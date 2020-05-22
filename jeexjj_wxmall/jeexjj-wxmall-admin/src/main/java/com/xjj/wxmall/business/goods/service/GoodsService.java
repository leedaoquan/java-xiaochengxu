/****************************************************
 * Description: Service for 商品
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.goods.service;
import com.xjj.wxmall.business.goods.entity.GoodsAttributeEntity;
import com.xjj.wxmall.business.goods.entity.GoodsEntity;
import com.xjj.wxmall.business.goods.entity.GoodsSpecificationEntity;

import java.util.List;

import com.xjj.framework.service.XjjService;

public interface GoodsService  extends XjjService<GoodsEntity>{
	
	/**
	 * 查询在售/下架商品总数
	 * @param onSale
	 * @return
	 */
	public int getCountByOnSale(int onSale);
	/**
	 * 查询
	 * @param brandId
	 * @param keyword
	 * @param isHot
	 * @param isNew
	 * @return
	 */
	public List<Long> getCategoryIds(Long brandId, String keyword, Boolean isHot, Boolean isNew);
	
	
	/**
	 * 上架
	 * @param ids
	 * @return
	 */
	public int onSale(Long[] ids);
	/**
	 * 下架
	 * @param ids
	 * @return
	 */
	public int cancleSale(Long[] ids);

	public List<GoodsEntity> findAllByCategoryId(Long categoryId);
	
	/**
	 * 保存
	 * @param obj
	 * @return
	 */
	public Long save(GoodsEntity goods,List<GoodsSpecificationEntity> specList,List<GoodsAttributeEntity> attrList);
	
}
