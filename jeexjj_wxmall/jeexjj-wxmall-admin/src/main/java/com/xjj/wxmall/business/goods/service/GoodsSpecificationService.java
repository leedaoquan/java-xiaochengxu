/****************************************************
 * Description: Service for 商品规格
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.business.goods.service;
import com.xjj.wxmall.business.goods.entity.GoodsSpecificationEntity;

import java.util.List;

import com.xjj.framework.service.XjjService;

public interface GoodsSpecificationService  extends XjjService<GoodsSpecificationEntity>{
	
	 /**
     * [
     *  {
     *      name: '',
     *      valueList: [ {}, {}]
     *  },
     *  {
     *      name: '',
     *      valueList: [ {}, {}]
     *  }
     *  ]
     *
     * @param id
     * @return
     */
    public Object getSpecificationVoList(Long id);
    
    /**
	 * 根据商品id查询product列表
	 * @param goodsId
	 * @return
	 */
	public List<GoodsSpecificationEntity> findListByGoodsId(Long goodsId);
	
	
	/**
	 * 批量更新
	 * @param specList
	 */
	public void updateBatch(List<GoodsSpecificationEntity> specList);
	
}
