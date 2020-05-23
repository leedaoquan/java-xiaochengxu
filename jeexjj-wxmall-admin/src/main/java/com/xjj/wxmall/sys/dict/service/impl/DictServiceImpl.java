package com.xjj.wxmall.sys.dict.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.wxmall.sys.dict.dao.DictDao;
import com.xjj.wxmall.sys.dict.entity.DictItem;
import com.xjj.wxmall.sys.dict.service.DictService;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;

@Service
public class DictServiceImpl extends XjjServiceSupport<DictItem> implements DictService{

	// 注入Service依赖
	@Autowired
	private DictDao dictDao;


	@Override
	public XjjDAO<DictItem> getDao() {
		
		return dictDao;
	}


}
