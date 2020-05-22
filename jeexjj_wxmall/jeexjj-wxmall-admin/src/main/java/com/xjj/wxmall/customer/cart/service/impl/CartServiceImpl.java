/****************************************************
 * Description: ServiceImpl for 购物车
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.customer.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.framework.Constants;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wxmall.customer.cart.entity.CartEntity;
import com.xjj.wxmall.customer.cart.dao.CartDao;
import com.xjj.wxmall.customer.cart.service.CartService;

@Service
public class CartServiceImpl extends XjjServiceSupport<CartEntity> implements CartService {

	@Autowired
	private CartDao cartDao;

	@Override
	public XjjDAO<CartEntity> getDao() {
		
		return cartDao;
	}
	
	public CartEntity queryExist(Long goodsId, Long productId, Long userId)
	{
		
		XJJParameter param = new XJJParameter();
        param.addQuery("query.customerId@eq@l",userId);
        param.addQuery("query.productId@eq@l",productId);
        param.addQuery("query.goodsId@eq@l",goodsId);
        
        List<CartEntity> catList = cartDao.findList(param.getQueryMap());
        
        if(null==catList || catList.size()==0)
        {
        	return null;
        }
	    return catList.get(0);    
	}
	public int updateCheck(Long userId, Long[] idArr, Boolean checked)
	{
		int check = 0;
		if(checked)
		{
			check = 1;
		}
		System.out.println("idsList===="+idArr);
		System.out.println("idsList.size===="+idArr.length);
		System.out.println("idArr====="+idArr);
		return cartDao.updateCheck(userId, idArr, check);
	}
	
	public static void main(String[] args) {
		 List<Long> idsList = new ArrayList<Long>();
		 
		 idsList.add(1L);
		 
		 Long[] ids = idsList.toArray(new Long[]{});
		 System.out.println(ids);
		 
	}
	public void deleteByProductId(Long userId, List<Long> idsList){
		cartDao.deleteByProductId(userId, idsList);
	}
	
	public List<CartEntity> queryByUidAndChecked(Long userId)
	{
		XJJParameter param = new XJJParameter();
        param.addQuery("query.customerId@eq@l",userId);
        param.addQuery("query.checked@eq@i",Constants.COMMON_SIMPLE_YES);
        
        List<CartEntity> cartList = cartDao.findList(param.getQueryMap());
        return cartList;
	}
	
	public void clearGoods(Long userId)
	{
		cartDao.clearGoods(userId);
	}
}