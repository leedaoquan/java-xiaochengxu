/****************************************************
 * Description: Controller for t_business_product
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-11 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.product.web;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjj.framework.exception.ValidationException;
import com.xjj.framework.json.XjjJson;
import com.xjj.framework.security.annotations.SecCreate;
import com.xjj.framework.security.annotations.SecDelete;
import com.xjj.framework.security.annotations.SecEdit;
import com.xjj.framework.security.annotations.SecList;
import com.xjj.framework.security.annotations.SecPrivilege;
import com.xjj.framework.web.SpringControllerSupport;
import com.xjj.framework.web.support.Pagination;
import com.xjj.wxmall.business.category.entity.CategoryEntity;
import com.xjj.wxmall.business.category.service.CategoryService;
import com.xjj.wxmall.business.goods.entity.GoodsEntity;
import com.xjj.wxmall.business.goods.entity.GoodsSpecificationEntity;
import com.xjj.wxmall.business.goods.service.GoodsService;
import com.xjj.wxmall.business.goods.service.GoodsSpecificationService;
import com.xjj.wxmall.business.product.entity.ProductEntity;
import com.xjj.wxmall.business.product.service.ProductService;

@Controller
@RequestMapping("/business/product")
public class ProductController extends SpringControllerSupport{
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private GoodsSpecificationService goodsSpecificationService;
	
	
	@SecPrivilege(title="SKU管理")
	@RequestMapping(value = "/index")
	public String index(Model model) {
		List<CategoryEntity> categoryList = this.findCategoryList();
		model.addAttribute("categoryList",categoryList);
		String page = this.getViewPath("index");
		return page;
	}
	
	@SecList
	@RequestMapping(value = "/list")
	public String list(Model model,
			@RequestParam(required=false,value="categoryId") Long categoryId,
			@RequestParam(required=false,value="goodsName") String goodsName,
			@ModelAttribute("page") Pagination page
			) {
		page = productService.findPageByParam(categoryId,goodsName,page);
		
		//把里面的specIds取出来
		Set<Long> specIdSet = new HashSet<Long>();
		Collection<ProductEntity> items = page.getItems();
		ProductEntity product = null;
		for (Iterator<ProductEntity> iterator = items.iterator(); iterator.hasNext();) {
			product =  iterator.next();
			specIdSet.addAll(Arrays.asList(product.getGoodsSpecificationIds()));
		}
		
		if(specIdSet.size()==0)
		{
			return getViewPath("list");
		}
		List<GoodsSpecificationEntity> specList = goodsSpecificationService.findListByColumnValues("id", specIdSet.toArray());
		//id对应规格的索引map
		Map<Long,GoodsSpecificationEntity> specMap = new HashMap<Long,GoodsSpecificationEntity>();
		for (int i = 0; i < specList.size(); i++) {
			specMap.put(specList.get(i).getId(),specList.get(i));
		}
		
		
		for (Iterator<ProductEntity> iterator = items.iterator(); iterator.hasNext();) {
			product =  iterator.next();
			product.setGoodsSpecificationNames(specMap);
		}
		
		return getViewPath("list");
	}
	
	/**
	 * 新增SKU
	 * @param product
	 * @param model
	 * @return
	 */
	@SecCreate
	@RequestMapping("/{categoryId}/input")
	public String create(
			@PathVariable("categoryId") Long categoryId,
			@ModelAttribute("product") ProductEntity product,
			Model model){
		
		CategoryEntity category  = categoryService.getById(categoryId);
		List<GoodsEntity> goodsList= goodsService.findAllByCategoryId(categoryId);
		
		model.addAttribute("category",category);
		model.addAttribute("goodsList",goodsList);
		return getViewPath("input");
	}
	
	/**
	 * 根据商品id,
	 * @param goodsId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getGoodsSpec/{goodsId}")
	public String getGoodsSpec(
			@PathVariable("goodsId") Long goodsId,
			Model model){
		Map<String,List<GoodsSpecificationEntity>> specMap = this.getSpecMap(goodsId);
		
		model.addAttribute("specMap",specMap);
		return getViewPath("spec-ajax");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		ProductEntity product = productService.getById(id);
		GoodsEntity goods = goodsService.getById(product.getGoodsId());
		CategoryEntity category = categoryService.getById(goods.getCategoryId());


		List<GoodsSpecificationEntity> specList = goodsSpecificationService.findListByColumnValues("id",
				product.getGoodsSpecificationIds());
		//id对应规格的索引map
		Map<Long,GoodsSpecificationEntity> specMap = new HashMap<Long,GoodsSpecificationEntity>();
		for (int i = 0; i < specList.size(); i++) {
			specMap.put(specList.get(i).getId(),specList.get(i));
		}
		product.setGoodsSpecificationNames(specMap);
		model.addAttribute("category",category);
		model.addAttribute("goods",goods);
		model.addAttribute("product",product);
		return getViewPath("edit");
	}
	
	
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(
			@RequestParam(required=false,value="specId") Long[] specIds,
			@ModelAttribute ProductEntity product){
		
		validateSave(product);
		// 判断规格是否为空
		if(null==specIds || 0==specIds.length){
			throw new ValidationException("请在【商品管理】里维护好商品规格信息后，再来添加SKU吧。");
		}
		if(product.isNew())
		{
			//判断是不是已经存在
			boolean exists = productService.isExistsSku(product.getGoodsId(), specIds);
			if(exists)
			{
				throw new ValidationException("本商品SKU已经存在，不能再添加了!");
			}
			product.setAddTime(new Date());
			product.setGoodsSpecificationIds(specIds);
			productService.save(product);
		}else
		{
			product.setGoodsSpecificationIds(specIds);
			product.setAddTime(new Date());
			productService.update(product);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(ProductEntity product){
		//必填项校验
		// 判断goods_id是否为空
		if(null==product.getGoodsId()){
			throw new ValidationException("校验失败，goods_id不能为空！");
		}
		// 判断goods_number是否为空
		if(null==product.getGoodsNumber()){
			throw new ValidationException("校验失败，goods_number不能为空！");
		}
		// 判断retail_price是否为空
		if(null==product.getRetailPrice()){
			throw new ValidationException("校验失败，retail_price不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		productService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			productService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
	
	
	
	/**
	 * sku单品库存设置
	 * @param goodsId
	 * @param model
	 * @return
	 */
	@RequestMapping("/sku/{goodsId}")
	public String sku(@PathVariable("goodsId") Long goodsId, Model model){
		
		List<ProductEntity> skuList = productService.findListByGoodsId(goodsId);
		//不存在，则初始化sku库存信息
		if(null== skuList || skuList.size()==0)
		{
			List<GoodsSpecificationEntity> specList = goodsSpecificationService.findListByProperty("goodsId", goodsId);
			Map<String,List<GoodsSpecificationEntity>> specMap = new TreeMap<String,List<GoodsSpecificationEntity>>();
			GoodsSpecificationEntity spec = null;
			for (int i = 0; i < specList.size(); i++) {
				spec = specList.get(i);
				if(specMap.containsKey(spec.getSpecification()))
				{
					specMap.get(spec.getSpecification()).add(spec);
				}else
				{
					List<GoodsSpecificationEntity> newSpecList = new ArrayList<GoodsSpecificationEntity>();
					newSpecList.add(spec);
					specMap.put(spec.getSpecification(), newSpecList);
				}
			}
			model.addAttribute("specMap",specMap);
		}
		
		model.addAttribute("goodsId",goodsId);
		return getViewPath("sku");
	}
	
	
	private Map<String,List<GoodsSpecificationEntity>> getSpecMap(Long goodsId)
	{
		List<GoodsSpecificationEntity> specList = goodsSpecificationService.findListByProperty("goodsId", goodsId);
		if(null==specList || specList.size()==0)
		{
			return null;
		}
		Map<String,List<GoodsSpecificationEntity>> specMap = new TreeMap<String,List<GoodsSpecificationEntity>>();
		GoodsSpecificationEntity spec = null;
		for (int i = 0; i < specList.size(); i++) {
			spec = specList.get(i);
			if(specMap.containsKey(spec.getSpecification()))
			{
				specMap.get(spec.getSpecification()).add(spec);
			}else
			{
				List<GoodsSpecificationEntity> newSpecList = new ArrayList<GoodsSpecificationEntity>();
				newSpecList.add(spec);
				specMap.put(spec.getSpecification(), newSpecList);
			}
		}
		
		return specMap;
	}
}

