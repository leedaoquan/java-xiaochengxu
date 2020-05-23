package com.xjj.wxmall.sec.web;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.xjj.framework.utils.Excel2007Util;
import com.xjj.framework.web.SpringControllerSupport;
import com.xjj.framework.web.support.Pagination;
import com.xjj.framework.web.support.QueryParameter;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wxmall.business.shop.entity.ShopEntity;
import com.xjj.wxmall.business.shop.service.ShopService;
import com.xjj.wxmall.sec.entity.RoleEntity;
import com.xjj.wxmall.sec.entity.UserRoleEntity;
import com.xjj.wxmall.sec.entity.XjjUser;
import com.xjj.wxmall.sec.service.RoleService;
import com.xjj.wxmall.sec.service.UserRoleService;
import com.xjj.wxmall.sec.service.UserService;

@Controller
@RequestMapping("/sec/manager")
public class ManagerController extends SpringControllerSupport{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@SecPrivilege(title="管理员管理")
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
		//查询的用户类型集合
		query.addQuery("query.userType@in@s",XJJConstants.USER_TYPE);
		query.addOrderByAsc("id");
		page = userService.findPage(query,page);
		
		//查询所有商店
		List<ShopEntity> shopList = shopService.findAll();
		model.addAttribute("shopList",shopList);
		
		return getViewPath("list");
	}
	
	//url : "${base}/sec/manager/setShop?userId="+userId+"&shopId="+shopId,
	@RequestMapping("/setShop")
	public @ResponseBody XjjJson  setShopManager(
			@RequestParam(value="userId") Long userId,
			@RequestParam(value="shopId") Long shopId){
		
		XjjUser user = userService.getById(userId);
		if(null==shopId && null!=user.getShopId())
		{
			user.setShopId(null);
			userService.update(user);
		}else if(!shopId.equals(user.getShopId()))
		{
			user.setShopId(shopId);
			userService.update(user);
		}
		return XjjJson.success("更新成功");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String input(@ModelAttribute("user") XjjUser user,Model model) {
		
		return getViewPath("input");
	}
	
	/*
	 * 修改用户
	 */
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		XjjUser user = userService.getById(id);
		model.addAttribute("user",user);
		return getViewPath("input");
	}
	
	
	
	/*
	 * 去添加角色
	 */
	@RequestMapping("/role/input/{userId}")
	public String role(@PathVariable("userId") Long userId, Model model){
		XjjUser user = userService.getById(userId);
		List<RoleEntity> roleList = roleService.findListNoUser(userId);
		model.addAttribute("roleList",roleList);
		model.addAttribute("user",user);
		return getViewPath("role");
	}
	
	
	@RequestMapping("/role/save")
	public @ResponseBody XjjJson  roleSave(
			@RequestParam(value="userId") Long userId,
			@RequestParam(value="roleIds") Long[] roleIds){
		
		if (null != roleIds) {
			for (int i = 0; i < roleIds.length; i++) {
				UserRoleEntity userRole = new UserRoleEntity();
				userRole.setUserId(userId);
				userRole.setRoleId(roleIds[i]);
				userRoleService.save(userRole);
			}
		}
		return XjjJson.success("保存成功");
	}
	
	@RequestMapping("/role/cancle/{userId}/{roleId}")
	public @ResponseBody XjjJson cancleRole(@PathVariable("userId") Long userId,
			@PathVariable("roleId") Long roleId){
		userRoleService.deleteBy2Id(userId,roleId);
		return XjjJson.success("成功删除1条");
	}
	
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	public @ResponseBody XjjJson  save(@ModelAttribute XjjUser user){
		
		if(user.isNew())
		{
			user.setCreateDate(new Date());
			userService.save(user);
		}else
		{
			userService.update(user);
		}
		return XjjJson.success("保存成功");
	}
	

	@RequestMapping(value = "/{userId}/detail", method = RequestMethod.POST)
	public String detail(@PathVariable("userId") Long userId, Model model) {
		if (userId == null) {
			return "redirect:/user/list";
		}
		XjjUser user = userService.getById(userId);
		if (user == null) {
			return "forward:/user/list";
		}
		model.addAttribute("user", user);
		return "detail";
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		userService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有删除");
		}
		for(Long id : ids){
			userService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
	
	
	/**
	 * 导入用户
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/import/save", method = RequestMethod.POST)
	public @ResponseBody XjjJson importSave(Model model,@RequestParam (value="fileId",required=false) Long fileId) {
		
		
		System.out.println("上传开始----");
		
		try {
   			Map<String,Object> map = userService.saveImportUser(fileId);
   			int allCnt = (Integer)map.get("allCnt");
   			return XjjJson.success("导入成功：本次共计导入数据"+allCnt+"条");
		} catch (ValidationException e) {
			
			return XjjJson.error("导入失败：<br/>"+e.getMessage());
		}
		
	}
	
	/**
	 * 导出用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/export/excel")
	public String exportExcel(HttpServletRequest request,HttpServletResponse response) {
		
		List<XjjUser>  userList =userService.findAll();
		
		LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
		columns.put("loginName", "账号");
		columns.put("userName", "用户名");
		columns.put("mobile", "手机");
		Excel2007Util.write(userList, columns,response,"user-export");
		
		return null;
	}
	
}
