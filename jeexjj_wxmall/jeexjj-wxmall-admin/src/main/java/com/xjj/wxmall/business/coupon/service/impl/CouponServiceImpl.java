/****************************************************
 * Description: ServiceImpl for 优惠券
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.business.coupon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wxmall.business.coupon.entity.CouponEntity;
import com.xjj.wxmall.business.coupon.dao.CouponDao;
import com.xjj.wxmall.business.coupon.service.CouponService;

@Service
public class CouponServiceImpl extends XjjServiceSupport<CouponEntity> implements CouponService {

	@Autowired
	private CouponDao couponDao;

	@Override
	public XjjDAO<CouponEntity> getDao() {
		
		return couponDao;
	}
}