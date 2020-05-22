/****************************************************
 * Description: ServiceImpl for 商品规格
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.XJJConstants;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wxmall.business.goods.entity.GoodsSpecificationEntity;
import com.xjj.wxmall.business.goods.dao.GoodsSpecificationDao;
import com.xjj.wxmall.business.goods.service.GoodsSpecificationService;

@Service
public class GoodsSpecificationServiceImpl extends XjjServiceSupport<GoodsSpecificationEntity> implements GoodsSpecificationService {

	@Autowired
	private GoodsSpecificationDao goodsSpecificationDao;

	@Override
	public XjjDAO<GoodsSpecificationEntity> getDao() {
		
		return goodsSpecificationDao;
	}
	
	private class VO {
        private String name;
        private List<GoodsSpecificationEntity> valueList;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public List<GoodsSpecificationEntity> getValueList() {
            return valueList;
        }

        public void setValueList(List<GoodsSpecificationEntity> valueList) {
            this.valueList = valueList;
        }
    }

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
    public Object getSpecificationVoList(Long id) {
        List<GoodsSpecificationEntity> goodsSpecificationList = findListByGoodsId(id);

        Map<String, VO> map = new HashMap<>();
        List<VO> specificationVoList = new ArrayList<>();

        for(GoodsSpecificationEntity goodsSpecification : goodsSpecificationList){
            String specification = goodsSpecification.getSpecification();
            VO goodsSpecificationVo = map.get(specification);
            if(goodsSpecificationVo == null){
                goodsSpecificationVo = new VO();
                goodsSpecificationVo.setName(specification);
                List<GoodsSpecificationEntity> valueList = new ArrayList<>();
                valueList.add(goodsSpecification);
                goodsSpecificationVo.setValueList(valueList);
                map.put(specification, goodsSpecificationVo);
                specificationVoList.add(goodsSpecificationVo);
            }
            else{
                List<GoodsSpecificationEntity> valueList = goodsSpecificationVo.getValueList();
                valueList.add(goodsSpecification);
            }
        }

        return specificationVoList;
    }
    
    
    /**
	 * 根据商品id查询product列表
	 * @param goodsId
	 * @return
	 */
	public List<GoodsSpecificationEntity> findListByGoodsId(Long goodsId)
	{
		XJJParameter query = new XJJParameter();
		query.addQuery("query.goodsId@eq@l", goodsId);
		List<GoodsSpecificationEntity> list = goodsSpecificationDao.findList(query.getQueryMap());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.xjj.wxmall.business.goods.service.GoodsSpecificationService#updateBatch(java.util.List)
	 */
	public void updateBatch(List<GoodsSpecificationEntity> specList)
	{
		if(null==specList)
		{
			return;
		}
		
		GoodsSpecificationEntity spec = null;
		for (int i = 0; i < specList.size(); i++) {
			spec = specList.get(i);
			//如果是0,说明是新添加的
			if(spec.getId().equals(0L))
			{
				spec.setAddTime(new Date());
				spec.setStatus(XJJConstants.COMMON_STATUS_VALID);
				goodsSpecificationDao.save(spec);
			}
			
			//如果是负数，说明是删除的
			if(spec.getId().intValue()<0)
			{
				goodsSpecificationDao.delete(Math.abs(spec.getId()));
			}
			
			//如果是正数，说明是修改的
			if(spec.getId().intValue()>0)
			{
				GoodsSpecificationEntity specDb = goodsSpecificationDao.getById(spec.getId());
				specDb.setSpecification(spec.getSpecification());
				specDb.setValue(spec.getValue());
				specDb.setPicUrl(spec.getPicUrl());
				goodsSpecificationDao.update(specDb);
			}
			
		}
	}
}