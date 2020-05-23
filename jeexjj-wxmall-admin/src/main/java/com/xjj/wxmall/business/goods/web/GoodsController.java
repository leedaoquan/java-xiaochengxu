/****************************************************
 * Description: Controller for 商品
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.goods.web;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjj.framework.XJJConstants;
import com.xjj.framework.exception.ValidationException;
import com.xjj.framework.json.XjjJson;
import com.xjj.framework.security.annotations.SecCreate;
import com.xjj.framework.security.annotations.SecDelete;
import com.xjj.framework.security.annotations.SecEdit;
import com.xjj.framework.security.annotations.SecList;
import com.xjj.framework.security.annotations.SecPrivilege;
import com.xjj.framework.web.SpringControllerSupport;
import com.xjj.framework.web.support.Pagination;
import com.xjj.framework.web.support.QueryParameter;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wxmall.business.brand.entity.BrandEntity;
import com.xjj.wxmall.business.brand.service.BrandService;
import com.xjj.wxmall.business.category.entity.CategoryEntity;
import com.xjj.wxmall.business.goods.entity.GoodsAttributeEntity;
import com.xjj.wxmall.business.goods.entity.GoodsEntity;
import com.xjj.wxmall.business.goods.entity.GoodsSpecificationEntity;
import com.xjj.wxmall.business.goods.service.GoodsAttributeService;
import com.xjj.wxmall.business.goods.service.GoodsService;
import com.xjj.wxmall.business.goods.service.GoodsSpecificationService;
import com.xjj.wxmall.business.product.entity.ProductEntity;
import com.xjj.wxmall.business.product.service.ProductService;

@Controller
@RequestMapping("/business/goods")
public class GoodsController extends SpringControllerSupport{
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsAttributeService goodsAttributeService;
	
	@Autowired
	private GoodsSpecificationService goodsSpecificationService;
	
	@Autowired
	private BrandService brandService;
	
	@SecPrivilege(title="商品管理")
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
			@QueryParameter XJJParameter query,
			@ModelAttribute("page") Pagination page
			) {
		page = goodsService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("goods") GoodsEntity goods,Model model){
		
		//查询品牌
		List<BrandEntity> brandList = brandService.findAll();
		model.addAttribute("brandList", brandList);
		model.addAttribute("categoryList",this.findCategoryList());
		
		return getViewPath("input");
	}
	
	/**
	 * 修改
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		
		//查询品牌
		List<BrandEntity> brandList = brandService.findAll();
		GoodsEntity goods = goodsService.getById(id);
		
		model.addAttribute("brandList", brandList);
		model.addAttribute("categoryList",this.findCategoryList());
		model.addAttribute("goods",goods);
		
		List<GoodsSpecificationEntity> specList = goodsSpecificationService.findListByProperty("goodsId", id);
		List<GoodsAttributeEntity> attrList = goodsAttributeService.findListByProperty("goodsId", id);
		model.addAttribute("specList",specList);
		model.addAttribute("attrList",attrList);
		return getViewPath("edit");
	}
	
	
	/**
	 * 新添加商品
	 * @param goods
	 * @param goodSpecName
	 * @param goodSpecVal
	 * @param goodSpecPic
	 * @param goodAttrName
	 * @param goodAttrVal
	 * @return
	 */
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute GoodsEntity goods,
			@RequestParam(required=false,value="goodSpecName") String[] goodSpecName,
			@RequestParam(required=false,value="goodSpecVal") String[] goodSpecVal,
			@RequestParam(required=false,value="goodSpecPic") String[] goodSpecPic,
			@RequestParam(required=false,value="goodAttrName") String[] goodAttrName,
			@RequestParam(required=false,value="goodAttrVal") String[] goodAttrVal
			){
		
		validateSave(goods);
		goods.setAddTime(new Date());
		//默认不上架
		goods.setIsOnSale(XJJConstants.COMMON_SIMPLE_NO);
		List<GoodsSpecificationEntity> specList = null;
		if(null!=goodSpecName)
		{
			specList = new ArrayList<GoodsSpecificationEntity>();
			for (int i = 0; i < goodSpecName.length; i++) {
				GoodsSpecificationEntity spec = new GoodsSpecificationEntity();
				spec.setSpecification(goodSpecName[i]);
				spec.setValue(goodSpecVal[i]);
				spec.setPicUrl(goodSpecPic[i]);
				spec.setAddTime(new Date());
				spec.setStatus(XJJConstants.COMMON_STATUS_VALID);
				specList.add(spec);
			}
		}
		
		List<GoodsAttributeEntity> attrList = null;
		
		if(null!=goodAttrName)
		{
			attrList = new ArrayList<GoodsAttributeEntity>();
			for (int i = 0; i < goodAttrName.length; i++) {
				GoodsAttributeEntity attr = new GoodsAttributeEntity();
				attr.setAttribute(goodAttrName[i]);
				attr.setValue(goodAttrVal[i]);
				attr.setAddTime(new Date());
				attrList.add(attr);
			}
		}
		
		//保存商品，及其规格、参数
		goodsService.save(goods,specList,attrList);
		
		
		return XjjJson.success("保存成功");
	}
	
	/**
	 * 更新商品
	 * @param goods
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody XjjJson update(@ModelAttribute GoodsEntity goods,
			@RequestParam(required=false,value="goodSpecId") Long[] goodSpecId,
			@RequestParam(required=false,value="goodSpecName") String[] goodSpecName,
			@RequestParam(required=false,value="goodSpecVal") String[] goodSpecVal,
			@RequestParam(required=false,value="goodSpecPic") String[] goodSpecPic,
			@RequestParam(required=false,value="goodAttrId") Long[] goodAttrId,
			@RequestParam(required=false,value="goodAttrName") String[] goodAttrName,
			@RequestParam(required=false,value="goodAttrVal") String[] goodAttrVal){
		
		GoodsEntity goodsDB = goodsService.getById(goods.getId());
		
		goodsDB.setName(goods.getName());
		goodsDB.setCategoryId(goods.getCategoryId());
		goodsDB.setGallery(goods.getGallery());
		goodsDB.setKeywords(goods.getKeywords());
		goodsDB.setGoodsBrief(goods.getGoodsBrief());
		goodsDB.setSortOrder(goods.getSortOrder());
		goodsDB.setCounterPrice(goods.getCounterPrice());
		goodsDB.setIsNewly(goods.getIsNewly());
		goodsDB.setIsHot(goods.getIsHot());
		goodsDB.setPrimaryPicUrl(goods.getPrimaryPicUrl());
		goodsDB.setListPicUrl(goods.getListPicUrl());
		goodsDB.setGoodsUnit(goods.getGoodsUnit());
		goodsDB.setGoodsDesc(goods.getGoodsDesc());
		goodsDB.setRetailPrice(goods.getRetailPrice());
		goodsService.update(goodsDB);
		
		//==============处理规格开始==============
		List<GoodsSpecificationEntity> specList = null;
		if(null!=goodSpecName)
		{
			specList = new ArrayList<GoodsSpecificationEntity>();
			for (int i = 0; i < goodSpecName.length; i++) {
				GoodsSpecificationEntity spec = new GoodsSpecificationEntity();
				spec.setId(goodSpecId[i]);
				spec.setGoodsId(goods.getId());
				spec.setSpecification(goodSpecName[i]);
				spec.setValue(goodSpecVal[i]);
				spec.setPicUrl(goodSpecPic[i]);
				specList.add(spec);
			}
			
			//批量更新规格
			goodsSpecificationService.updateBatch(specList);
		}
		
		
		//==============处理参数开始==============
		List<GoodsAttributeEntity> attrList = null;
		if(null!=goodAttrName)
		{
			attrList = new ArrayList<GoodsAttributeEntity>();
			for (int i = 0; i < goodAttrName.length; i++) {
				GoodsAttributeEntity attr = new GoodsAttributeEntity();
				attr.setId(goodAttrId[i]);
				attr.setAttribute(goodAttrName[i]);
				attr.setValue(goodAttrVal[i]);
				attr.setGoodsId(goods.getId());
				attrList.add(attr);
			}
			
			//批量更新参数
			goodsAttributeService.updateBatch(attrList);
			
		}
		
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(GoodsEntity goods){
		//必填项校验
		// 判断brand_id是否为空
		if(null==goods.getBrandId()){
			throw new ValidationException("校验失败，brand_id不能为空！");
		}
		// 判断category_id是否为空
		if(null==goods.getCategoryId()){
			throw new ValidationException("校验失败，category_id不能为空！");
		}
		
		// 判断名称是否为空
		if(null==goods.getName()){
			throw new ValidationException("校验失败，名称不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		goodsService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			goodsService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
	
	/**
	 * 上架
	 * @param ids
	 * @return
	 */
	@RequestMapping("/onSale")
	public @ResponseBody XjjJson onSale(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择要上架的商品记录");
		}
		int count = goodsService.onSale(ids);
		return XjjJson.success("成功上架"+count+"个商品");
	}
	/**
	 * 上架
	 * @param ids
	 * @return
	 */
	@RequestMapping("/cancleSale")
	public @ResponseBody XjjJson cancleSale(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择要下架的商品记录");
		}
		int count = goodsService.cancleSale(ids);
		return XjjJson.success("成功下架"+count+"个商品");
	}
	
	
}

