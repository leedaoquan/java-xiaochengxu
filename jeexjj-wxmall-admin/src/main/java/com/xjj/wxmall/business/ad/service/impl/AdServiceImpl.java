/****************************************************
 * Description: ServiceImpl for 广告
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.business.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wxmall.business.ad.entity.AdEntity;
import com.xjj.wxmall.business.ad.dao.AdDao;
import com.xjj.wxmall.business.ad.service.AdService;

@Service
public class AdServiceImpl extends XjjServiceSupport<AdEntity> implements AdService {

	@Autowired
	private AdDao adDao;

	@Override
	public XjjDAO<AdEntity> getDao() {
		
		return adDao;
	}
}