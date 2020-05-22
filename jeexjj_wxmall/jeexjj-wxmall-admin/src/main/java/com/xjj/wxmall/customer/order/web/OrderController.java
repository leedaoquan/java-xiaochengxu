/****************************************************
 * Description: Controller for 订单
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.customer.order.web;
import java.util.Date;
import java.util.List;

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
import com.xjj.framework.web.support.QueryParameter;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wxmall.customer.order.entity.OrderEntity;
import com.xjj.wxmall.customer.order.entity.OrderGoodsEntity;
import com.xjj.wxmall.customer.order.service.OrderGoodsService;
import com.xjj.wxmall.customer.order.service.OrderService;
import com.xjj.wxmall.util.OrderUtil;

@Controller
@RequestMapping("/customer/order")
public class OrderController extends SpringControllerSupport{
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	
	
	@SecPrivilege(title="订单管理")
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
		page = orderService.findPage(query,page);
		return getViewPath("list");
	}
	
	@RequestMapping("/view/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		OrderEntity order = orderService.getById(id);
		List<OrderGoodsEntity> goodsList = orderGoodsService.findListByProperty("orderId",id);
		model.addAttribute("order",order);
		model.addAttribute("goodsList",goodsList);
		
		return getViewPath("view");
	}
	
	/**
	 * 发货(去发货页面)
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/deliver/{id}")
	public String deliver(@PathVariable("id") Long id, Model model){
		OrderEntity order = orderService.getById(id);
		model.addAttribute("order",order);
		return getViewPath("deliver");
	}
	
	/**
	 * 保存发货信息
	 * @param order
	 * @return
	 */
	@RequestMapping("/shipped/{id}")
	public @ResponseBody XjjJson shipped(@PathVariable("id") Long id, @ModelAttribute OrderEntity order){
		
		OrderEntity orderDB = orderService.getById(id);
		
		orderDB.setOrderStatus(OrderUtil.STATUS_SHIP);
		orderDB.setShipSn(order.getShipSn());
		orderDB.setShipStartTime(new Date());
		orderDB.setShipChannel(order.getShipChannel());
		
		orderService.update(orderDB);
		return XjjJson.success("保存成功");
	}
	
	/**
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/received")
	public @ResponseBody XjjJson received(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择已送达的订单");
		}
		int count = 0;
		OrderEntity order = null;
		for(Long id : ids){
			 order = orderService.getById(id);
			 
			 if(OrderUtil.STATUS_SHIP.equals(order.getOrderStatus()))
			 {
				 order.setOrderStatus(OrderUtil.STATUS_AUTO_CONFIRM);
				 order.setShipEndTime(new Date());
				 orderService.update(order);
				 count++;
			 }else
			 {
				 continue;
			 }
		}
		return XjjJson.success("成功送达"+count+"个订单");
	}
	
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		orderService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			orderService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

