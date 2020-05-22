/****************************************************
 * Description: Service for 类目
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.category.service;
import com.xjj.wxmall.business.category.entity.CategoryEntity;

import java.util.List;

import com.xjj.framework.service.XjjService;

public interface CategoryService  extends XjjService<CategoryEntity>{
	
	/**
	 * 根据pid查询类目
	 * @param pid
	 * @return
	 */
	public List<CategoryEntity> findListByPid(Long pid);

	public void deleteByPid(Long pid);
	
	/**
	 * 查询一级有效类目
	 * @return
	 */
	public List<CategoryEntity> findListByLevel1();
}
