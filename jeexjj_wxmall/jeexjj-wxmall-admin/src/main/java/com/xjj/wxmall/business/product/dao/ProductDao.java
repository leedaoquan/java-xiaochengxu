/****************************************************
 * Description: DAO for t_business_product
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xjj.framework.dao.XjjDAO;
import com.xjj.wxmall.business.product.entity.ProductEntity;

public interface ProductDao  extends XjjDAO<ProductEntity> {
	
	public List<ProductEntity> findPageByParam (@Param("categoryId") Long categoryId,@Param("goodsName") String goodsName,@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 
	 * @param goodsId
	 * @param specIds
	 * @return
	 */
	public int countSkuByParam(@Param("goodsId") Long goodsId,@Param("specIds") String specIds);
}

