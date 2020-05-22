/****************************************************
 * Description: ServiceImpl for t_sys_xfile
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-05-04 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.sys.xfile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wxmall.sys.xfile.entity.XfileEntity;
import com.xjj.wxmall.sys.xfile.dao.XfileDao;
import com.xjj.wxmall.sys.xfile.service.XfileService;

@Service
public class XfileServiceImpl extends XjjServiceSupport<XfileEntity> implements XfileService {

	@Autowired
	private XfileDao xfileDao;

	@Override
	public XjjDAO<XfileEntity> getDao() {
		
		return xfileDao;
	}
}