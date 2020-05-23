/****************************************************
 * Description: DAO for 类目
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.category.dao;

import com.xjj.wxmall.business.category.entity.CategoryEntity;
import com.xjj.framework.dao.XjjDAO;

public interface CategoryDao  extends XjjDAO<CategoryEntity> {
	void deleteByPid(Long pid);
}

