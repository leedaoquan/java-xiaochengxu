package com.xjj.wxmall.wx.web;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WxBaseController{
	public HttpServletRequest getRequest()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	
	/**
	 * list转为数组
	 * @param list
	 * @return
	 */
	public Long[] toLongArray(List<Long> list)
	{
		if(null==list)
		{
			return null;
		}
		Long[] arr = new Long[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			arr[i]=list.get(i);
		}
		return arr;
	}

}
