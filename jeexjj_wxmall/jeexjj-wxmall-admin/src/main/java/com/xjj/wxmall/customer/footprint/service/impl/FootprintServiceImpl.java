/****************************************************
 * Description: ServiceImpl for t_customer_footprint
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.customer.footprint.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wxmall.customer.footprint.entity.FootprintEntity;
import com.xjj.wxmall.customer.footprint.dao.FootprintDao;
import com.xjj.wxmall.customer.footprint.service.FootprintService;

@Service
public class FootprintServiceImpl extends XjjServiceSupport<FootprintEntity> implements FootprintService {

	@Autowired
	private FootprintDao footprintDao;

	@Override
	public XjjDAO<FootprintEntity> getDao() {
		
		return footprintDao;
	}
}