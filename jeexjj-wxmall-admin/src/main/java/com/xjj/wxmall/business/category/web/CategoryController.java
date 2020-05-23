/****************************************************
 * Description: Controller for 类目
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.category.web;
import java.util.Date;

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
import com.xjj.wxmall.business.category.entity.CategoryEntity;
import com.xjj.wxmall.business.category.service.CategoryService;

@Controller
@RequestMapping("/business/category")
public class CategoryController extends SpringControllerSupport{
	@Autowired
	private CategoryService categoryService;
	
	
	@SecPrivilege(title="类目管理")
	@RequestMapping(value = "/index")
	public String index(Model model) {
		String page = this.getViewPath("index");
		return page;
	}
	
	
	@SecList
	@RequestMapping(value = "/list")
	public String list(Model model,
			@QueryParameter XJJParameter query,
			@ModelAttribute("page") Pagination page
			) {
		
		//设置类目
		model.addAttribute("categoryList",this.findCategoryList());
		return getViewPath("list");
		
	}
	
//	@SecCreate
//	@RequestMapping("/input")
//	public String create(@ModelAttribute("category") CategoryEntity category,Model model){
//		return getViewPath("input");
//	}
	
	/**
	 * 增加类目
	 * @param pid 父类目，0为根类目
	 * @param model
	 * @return
	 */
	
	@SecCreate
	@RequestMapping("/{pid}/input")
	public String create(
			@PathVariable("pid") Long pid,
			@ModelAttribute("category") CategoryEntity category,
			Model model){
		
		CategoryEntity parentCategory = null;
		if(pid.intValue()==0)
		{
			parentCategory = new CategoryEntity();
			parentCategory.setId(0L);
			parentCategory.setName("根目录");
		}else
		{
			parentCategory = categoryService.getById(pid);
		}
		model.addAttribute("parentCategory",parentCategory);
		model.addAttribute("pid",pid);
		return getViewPath("input");
	}
	
	/**
	 * 修改
	 * @param id
	 * @param model
	 * @return
	 */
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		CategoryEntity category = categoryService.getById(id);
		model.addAttribute("category",category);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute CategoryEntity category){
		
		validateSave(category);
		if(category.isNew())
		{
			category.setAddTime(new Date());
			if(category.getParentId().intValue()==0)
			{
				category.setLevel("L1");
			}else
			{
				category.setLevel("L2");
				
			}
			category.setStatus(XJJConstants.COMMON_STATUS_INVALID);
			categoryService.save(category);
		}else
		{
			CategoryEntity categoryDB = categoryService.getById(category.getId());
			//BeanUtils.copyProperties(category, categoryDB, "addTime");
			categoryDB.setImgUrl(category.getImgUrl());
			categoryDB.setIconUrl(category.getIconUrl());
			categoryDB.setName(category.getName());
			categoryDB.setFrontDesc(category.getFrontDesc());
			categoryService.update(categoryDB);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(CategoryEntity category){
		//必填项校验
		// 判断shop_id是否为空
		// 判断名称是否为空
		if(null==category.getName()){
			throw new ValidationException("校验失败，名称不能为空！");
		}
		// 判断front_desc是否为空
		if(null==category.getFrontDesc()){
			throw new ValidationException("校验失败，front_desc不能为空！");
		}
//		// 判断parent_id是否为空
//		if(null==category.getParentId()){
//			throw new ValidationException("校验失败，parent_id不能为空！");
//		}
//		// 判断sort_order是否为空
//		if(null==category.getSortOrder()){
//			throw new ValidationException("校验失败，sort_order不能为空！");
//		}
//		// 判断show_index是否为空
//		if(null==category.getShowIndex()){
//			throw new ValidationException("校验失败，show_index不能为空！");
//		}
//		// 判断is_show是否为空
//		if(null==category.getIsShow()){
//			throw new ValidationException("校验失败，is_show不能为空！");
//		}
//		// 判断level是否为空
//		if(null==category.getLevel()){
//			throw new ValidationException("校验失败，level不能为空！");
//		}
//		// 判断type是否为空
//		if(null==category.getType()){
//			throw new ValidationException("校验失败，type不能为空！");
//		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		categoryService.delete(id);
		categoryService.deleteByPid(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			categoryService.delete(id);
			categoryService.deleteByPid(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
	
	
	@RequestMapping("/disable/{id}")
	public @ResponseBody XjjJson disable(@PathVariable("id") Long id){
		CategoryEntity category = categoryService.getById(id);
		category.setStatus(XJJConstants.COMMON_STATUS_INVALID);
		categoryService.update(category);
		return XjjJson.success("禁用类目成功！");
	}
	
	
	@RequestMapping("/enable/{id}")
	public @ResponseBody XjjJson enable(@PathVariable("id") Long id){
		CategoryEntity category = categoryService.getById(id);
		category.setStatus(XJJConstants.COMMON_STATUS_VALID);
		categoryService.update(category);
		return XjjJson.success("启用类目成功！");
	}
}

