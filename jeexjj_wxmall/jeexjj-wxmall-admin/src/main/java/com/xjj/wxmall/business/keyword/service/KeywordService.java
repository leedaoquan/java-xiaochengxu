/****************************************************
 * Description: Service for 关键词
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.keyword.service;
import com.xjj.wxmall.business.keyword.entity.KeywordEntity;
import com.xjj.framework.service.XjjService;

public interface KeywordService  extends XjjService<KeywordEntity>{
	
	/**
	 * 查询默认列表
	 * @return
	 */
	public KeywordEntity getDefault();
}
