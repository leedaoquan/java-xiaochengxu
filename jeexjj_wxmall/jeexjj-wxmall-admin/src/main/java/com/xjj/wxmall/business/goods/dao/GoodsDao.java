/****************************************************
 * Description: DAO for 商品
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.goods.dao;

import org.apache.ibatis.annotations.Param;

import com.xjj.framework.dao.XjjDAO;
import com.xjj.wxmall.business.goods.entity.GoodsEntity;

import java.util.List;

public interface GoodsDao  extends XjjDAO<GoodsEntity> {
	
	public int onSale(@Param("ids") Object[] ids);
	public int cancleSale(@Param("ids") Object[] ids);
	public List<GoodsEntity> findAllByCategoryId(@Param("categoryId") Long categoryId);
}

