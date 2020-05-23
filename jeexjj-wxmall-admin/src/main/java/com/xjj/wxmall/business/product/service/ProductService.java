/****************************************************
 * Description: Service for t_business_product
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.product.service;
import java.util.List;

import com.xjj.framework.service.XjjService;
import com.xjj.framework.web.support.Pagination;
import com.xjj.wxmall.business.product.entity.ProductEntity;

public interface ProductService  extends XjjService<ProductEntity>{
	/**
	 * 根据商品id查询product列表
	 * @param goodsId
	 * @return
	 */
	public List<ProductEntity> findListByGoodsId(Long goodsId); 
	
	/**
	 * 自定义分页查询
	 * @param categoryId
	 * @param goodsName
	 * @param page
	 * @return
	 */
	public Pagination findPageByParam(Long categoryId,String goodsName, Pagination page);
	
	/**
	 * 验证sku是否已经存在
	 * @param goodsId
	 * @param specIds
	 * @return
	 */
	public boolean isExistsSku(Long goodsId,Long[] specIds); 
	
}
