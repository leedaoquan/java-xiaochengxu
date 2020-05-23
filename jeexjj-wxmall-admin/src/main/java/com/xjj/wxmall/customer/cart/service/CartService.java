/****************************************************
 * Description: Service for 购物车
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.customer.cart.service;
import com.xjj.wxmall.customer.cart.entity.CartEntity;

import java.util.List;

import com.xjj.framework.service.XjjService;

public interface CartService  extends XjjService<CartEntity>{
	
	public CartEntity queryExist(Long goodsId, Long productId, Long userId);
	public int updateCheck(Long userId, Long[] idArr, Boolean checked);
	public void deleteByProductId(Long userId, List<Long> idsList);
	
	/**
	 * 根据用户id和checked,查询
	 * @param userId
	 * @param checked
	 * @return
	 */
	public List<CartEntity> queryByUidAndChecked(Long userId);
	
	/**
	 * 删除购物车里面的商品信息
	 * @param userId
	 */
	public void clearGoods(Long userId);
}
