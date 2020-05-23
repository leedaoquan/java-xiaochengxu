/****************************************************
 * Description: ServiceImpl for 客户优惠券
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.customer.buyer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wxmall.customer.buyer.entity.BuyerCouponEntity;
import com.xjj.wxmall.customer.buyer.dao.BuyerCouponDao;
import com.xjj.wxmall.customer.buyer.service.BuyerCouponService;

@Service
public class BuyerCouponServiceImpl extends XjjServiceSupport<BuyerCouponEntity> implements BuyerCouponService {

	@Autowired
	private BuyerCouponDao buyerCouponDao;

	@Override
	public XjjDAO<BuyerCouponEntity> getDao() {
		
		return buyerCouponDao;
	}
}