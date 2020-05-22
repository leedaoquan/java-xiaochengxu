/****************************************************
 * Description: ServiceImpl for t_business_product
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.business.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.dao.CommonDao;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.framework.utils.StringUtils;
import com.xjj.framework.web.support.Pagination;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wxmall.business.product.entity.ProductEntity;
import com.xjj.wxmall.business.product.dao.ProductDao;
import com.xjj.wxmall.business.product.service.ProductService;

/**
 * @author zhanghejie
 *
 */
/**
 * @author zhanghejie
 *
 */
/**
 * @author zhanghejie
 *
 */
@Service
public class ProductServiceImpl extends XjjServiceSupport<ProductEntity> implements ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CommonDao commonDao;

	@Override
	public XjjDAO<ProductEntity> getDao() {
		
		return productDao;
	}
	
	/**
	 * 根据商品id查询product列表
	 * @param goodsId
	 * @return
	 */
	public List<ProductEntity> findListByGoodsId(Long goodsId)
	{
		XJJParameter query = new XJJParameter();
		query.addQuery("query.goodsId@eq@l", goodsId);
		List<ProductEntity> list = productDao.findList(query.getQueryMap());
		 return list;
	}
	
	/**
	 * 自定义分页查询
	 */
	public Pagination findPageByParam(Long categoryId,String goodsName, Pagination page)
	{
		
		String countSql = "select count(p.id) from t_business_product p,t_business_goods g where p.goods_id=g.id";
		if(null!=categoryId)
		{
			countSql+=" and ( g.category_id = "+categoryId+" or" +
					" g.category_id in (select id from t_business_category where parent_id="+categoryId+" and status='valid'))";
		}
		
		if(!StringUtils.isBlank(goodsName))
		{
			goodsName="%"+goodsName+"%";
			countSql+=" and g.name like '"+goodsName+"'";
		}
		System.out.println("===countSql===="+countSql);
		int totalRecord = commonDao.getCountBySQL(countSql);
		page.setTotalRecord(totalRecord);
			
		int limit  = page.getPageSize();
		int currentPage = page.getCurrentPage();
		int offset = (currentPage-1)*limit;
		
		page.setItems(productDao.findPageByParam(categoryId,goodsName,offset,limit));
		return page;
	}
	
	
	public boolean isExistsSku(Long goodsId,Long[] specIds)
	{
		String ids = StringUtils.join(specIds, ", ");
		int count = productDao.countSkuByParam(goodsId, "["+ids+"]");
		if(count>0)
		{
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		Long[] aa = new Long[]{5l,6l};
		Long a =88l;
		System.out.println(StringUtils.join(aa," ,"));
		System.out.println(a);
	}
}