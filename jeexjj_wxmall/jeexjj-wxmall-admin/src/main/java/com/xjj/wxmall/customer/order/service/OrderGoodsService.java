/****************************************************
 * Description: Service for 订单商品
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.customer.order.service;
import com.xjj.wxmall.customer.order.entity.OrderGoodsEntity;

import java.util.List;

import com.xjj.framework.service.XjjService;

public interface OrderGoodsService  extends XjjService<OrderGoodsEntity>{
	
	public List<OrderGoodsEntity> findByOidAndGid(Long orderId,Long goodsId);
}
