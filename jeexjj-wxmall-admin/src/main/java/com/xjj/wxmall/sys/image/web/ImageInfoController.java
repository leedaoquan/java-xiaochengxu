/****************************************************
 * Description: Controller for t_sys_image_info
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-11-11 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.sys.image.web;
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
import com.xjj.framework.utils.FileUtils;
import com.xjj.framework.utils.StringUtils;
import com.xjj.framework.web.SpringControllerSupport;
import com.xjj.framework.web.support.Pagination;
import com.xjj.framework.web.support.QueryParameter;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wxmall.sys.image.entity.ImageInfoEntity;
import com.xjj.wxmall.sys.image.service.ImageInfoService;

@Controller
@RequestMapping("/sys/image/info")
public class ImageInfoController extends SpringControllerSupport{
	@Autowired
	private ImageInfoService imageInfoService;
	
	
	@SecPrivilege(title="图片管理")
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
		page = imageInfoService.findPage(query,page);
		return getViewPath("list");
	}
	
	/**
	 * 查询选择图片
	 * @param model
	 * @param multi 是否可以选择多个
	 * @param cbId 回调,用它来标识选择完图片以后，回调哪个方法。
	 * @param query
	 * @param page
	 * @return
	 */
	@SecList
	@RequestMapping(value = "/select")
	public String select(Model model,
			@RequestParam(value="multi",defaultValue="true") Boolean multi,
			@RequestParam(value="cbId",defaultValue="cb") String cbId,
			@QueryParameter XJJParameter query,
			@ModelAttribute("page") Pagination page
			) {
		//
		page.setPageSize(8);
		
		String imgTitle = this.getRequest().getParameter("query.imgTitle@lk@s");
		String imgKeywords = this.getRequest().getParameter("query.imgKeywords@lk@s");
		if(!StringUtils.isBlank(imgTitle))
		{
			model.addAttribute("imgTitle",imgTitle);
		}
		if(!StringUtils.isBlank(imgKeywords))
		{
			model.addAttribute("imgKeywords",imgKeywords);
		}
		
		
		model.addAttribute("multi",multi);
		model.addAttribute("cbId",cbId);
		
		page = imageInfoService.findPage(query,page);
		return getViewPath("select");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("imageInfo") ImageInfoEntity imageInfo,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		ImageInfoEntity imageInfo = imageInfoService.getById(id);
		model.addAttribute("imageInfo",imageInfo);
		return getViewPath("input");
	}
	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") Long id, Model model){
		ImageInfoEntity imageInfo = imageInfoService.getById(id);
		model.addAttribute("imageInfo",imageInfo);
		return getViewPath("view");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute ImageInfoEntity imageInfo){
		
		//validateSave(imageInfo);
		if(imageInfo.isNew())
		{
			imageInfoService.save(imageInfo);
		}else
		{
			ImageInfoEntity imgDb = imageInfoService.getById(imageInfo.getId());
			imgDb.setImgTitle(imageInfo.getImgTitle());
			imgDb.setImgWidth(imageInfo.getImgWidth());
			imgDb.setImgHeight(imageInfo.getImgHeight());
			imgDb.setImgKeywords(imageInfo.getImgKeywords());
			imageInfoService.update(imgDb);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(ImageInfoEntity imageInfo){
		//必填项校验
		// 判断路径是否为空
		if(null==imageInfo.getImgPath()){
			throw new ValidationException("校验失败，路径不能为空！");
		}
		// 判断标题是否为空
		if(null==imageInfo.getImgTitle()){
			throw new ValidationException("校验失败，标题不能为空！");
		}
		// 判断创建时间是否为空
		if(null==imageInfo.getCreateDate()){
			throw new ValidationException("校验失败，创建时间不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		imageInfoService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		
		ImageInfoEntity imageInfo = null;
		for(Long id : ids){
			
			imageInfo = imageInfoService.getById(id);
			imageInfoService.delete(imageInfo);
			
			try {
				FileUtils.deleteFile(imageInfo.getImgPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

