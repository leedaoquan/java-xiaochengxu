/****************************************************
 * Description: ServiceImpl for 商品
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.business.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wxmall.business.goods.entity.GoodsAttributeEntity;
import com.xjj.wxmall.business.goods.entity.GoodsEntity;
import com.xjj.wxmall.business.goods.entity.GoodsSpecificationEntity;
import com.xjj.wxmall.business.goods.dao.GoodsAttributeDao;
import com.xjj.wxmall.business.goods.dao.GoodsDao;
import com.xjj.wxmall.business.goods.dao.GoodsSpecificationDao;
import com.xjj.wxmall.business.goods.service.GoodsService;

@Service
public class GoodsServiceImpl extends XjjServiceSupport<GoodsEntity> implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private GoodsSpecificationDao goodsSpecificationDao;
	@Autowired
	private GoodsAttributeDao goodsAttributeDao;

	@Override
	public XjjDAO<GoodsEntity> getDao() {
		
		return goodsDao;
	}
	
	/**
	 * 查询在售/下架商品总数
	 * @param onSale
	 * @return
	 */
	public int getCountByOnSale(int onSale)
	{
		XJJParameter param = new XJJParameter();
		param.addQuery("query.isOnSale@eq@i", onSale);
        int count = goodsDao.getCount(param.getQueryMap());
        return count;
	}
	
	
	public List<Long> getCategoryIds(Long brandId, String keyword, Boolean isHot, Boolean isNew) {

        XJJParameter param = new XJJParameter();
        param.addQuery("query.brandId@eq@l",brandId);
        param.addQuery("query.keyword@lk@s",keyword);
        
        if(null!=isHot)
        {
        	param.addQuery("query.isHot@eq@i",isHot?1:0);
        }
        
        if(null!=isNew)
        {
        	param.addQuery("query.isNewly@eq@i",isNew?1:0);
        }
        
        List<GoodsEntity> goodsList = goodsDao.findList(param.getQueryMap());
        List<Long> cats = new ArrayList<Long>();
        for(GoodsEntity goods : goodsList){
            cats.add(goods.getCategoryId());
        }
        return cats;
    }
	
	/* (non-Javadoc)
	 * @see com.xjj.wxmall.business.goods.service.GoodsService#onSale(java.lang.Long[])
	 */
	public int onSale(Long[] ids)
	{
		return goodsDao.onSale(ids);
	}
	public int cancleSale(Long[] ids)
	{
		return goodsDao.cancleSale(ids);
	}

	@Override
	public List<GoodsEntity> findAllByCategoryId(Long categoryId) {
		return goodsDao.findAllByCategoryId(categoryId);
	}

	/**
	 * 保存
	 * @param
	 * @return
	 */
	public Long save(GoodsEntity goods,List<GoodsSpecificationEntity> specList,List<GoodsAttributeEntity> attrList)
	{
		goodsDao.save(goods);
		Long goodsId = goods.getId();
		
		if(null!=specList)
		{
			GoodsSpecificationEntity goodsSpec = null;
			for (int i = 0; i < specList.size(); i++) {
				goodsSpec = specList.get(i);
				goodsSpec.setGoodsId(goodsId);
				goodsSpecificationDao.save(goodsSpec);
			}
		}
		if(null!=attrList)
		{
			GoodsAttributeEntity goodsAttr = null;
			for (int i = 0; i < attrList.size(); i++) {
				goodsAttr = attrList.get(i);
				goodsAttr.setGoodsId(goodsId);
				goodsAttributeDao.save(goodsAttr);
			}
		}
		return goodsId;
	}
	
}