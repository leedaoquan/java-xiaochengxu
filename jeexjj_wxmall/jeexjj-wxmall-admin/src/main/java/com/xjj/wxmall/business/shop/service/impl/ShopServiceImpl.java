/****************************************************
 * Description: ServiceImpl for 商家
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.business.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wxmall.business.shop.dao.ShopDao;
import com.xjj.wxmall.business.shop.entity.ShopEntity;
import com.xjj.wxmall.business.shop.service.ShopService;

@Service
public class ShopServiceImpl extends XjjServiceSupport<ShopEntity> implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Override
	public XjjDAO<ShopEntity> getDao() {
		
		return shopDao;
	}
}