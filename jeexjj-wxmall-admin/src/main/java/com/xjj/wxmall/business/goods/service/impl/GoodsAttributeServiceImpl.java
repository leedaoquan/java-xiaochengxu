/****************************************************
 * Description: ServiceImpl for t_business_goods_attribute
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.business.goods.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.XJJConstants;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wxmall.business.goods.entity.GoodsAttributeEntity;
import com.xjj.wxmall.business.goods.entity.GoodsSpecificationEntity;
import com.xjj.wxmall.business.goods.dao.GoodsAttributeDao;
import com.xjj.wxmall.business.goods.service.GoodsAttributeService;

@Service
public class GoodsAttributeServiceImpl extends XjjServiceSupport<GoodsAttributeEntity> implements GoodsAttributeService {

	@Autowired
	private GoodsAttributeDao goodsAttributeDao;

	@Override
	public XjjDAO<GoodsAttributeEntity> getDao() {
		
		return goodsAttributeDao;
	}
	
	/**
	 * 根据商品id查询属性列表
	 * @param goodsId
	 * @return
	 */
	public List<GoodsAttributeEntity> findListByGoodsId(Long goodsId)
	{
		XJJParameter query = new XJJParameter();
		query.addQuery("query.goodsId@eq@l", goodsId);
		List<GoodsAttributeEntity> list = goodsAttributeDao.findList(query.getQueryMap());
		 return list;
	}
	
	/**
	 * 批量更新
	 * @param attrList
	 */
	public void updateBatch(List<GoodsAttributeEntity> attrList)
	{

		if(null==attrList)
		{
			return;
		}
		
		GoodsAttributeEntity attr = null;
		for (int i = 0; i < attrList.size(); i++) {
			attr = attrList.get(i);
			//如果是0,说明是新添加的
			if(attr.getId().equals(0L))
			{
				attr.setAddTime(new Date());
				goodsAttributeDao.save(attr);
			}
			
			//如果是负数，说明是删除的
			if(attr.getId().intValue()<0)
			{
				goodsAttributeDao.delete(Math.abs(attr.getId()));
			}
			
			//如果是正数，说明是修改的
			if(attr.getId().intValue()>0)
			{
				GoodsAttributeEntity attrDb = goodsAttributeDao.getById(attr.getId());
				attrDb.setAttribute(attr.getAttribute());
				attrDb.setValue(attr.getValue());
				goodsAttributeDao.update(attrDb);
			}
			
		}
	
	}
}