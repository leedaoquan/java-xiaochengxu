/****************************************************
 * Description: ServiceImpl for 搜索历史
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.customer.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wxmall.customer.search.entity.SearchHistoryEntity;
import com.xjj.wxmall.customer.search.dao.SearchHistoryDao;
import com.xjj.wxmall.customer.search.service.SearchHistoryService;

@Service
public class SearchHistoryServiceImpl extends XjjServiceSupport<SearchHistoryEntity> implements SearchHistoryService {

	@Autowired
	private SearchHistoryDao searchHistoryDao;

	@Override
	public XjjDAO<SearchHistoryEntity> getDao() {
		
		return searchHistoryDao;
	}
}