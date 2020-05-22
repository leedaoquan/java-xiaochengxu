package com.xjj.framework.entity;

import com.xjj.framework.utils.StringUtils;
import com.xjj.wxmall.util.PropertiesUtil;

public class EntitySupport{
	
	public Long id;
	
	public boolean isNew() {
		return null==id || id.longValue()<=0;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 指示其他某个对象是否与此对象“相等”
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if (this == obj){
			return true;
		}
		if (!getClass().equals(obj.getClass())){
			return false;
		}
		
		EntitySupport that = (EntitySupport)obj;
		if(that.getId()==null || !getId().equals(that.getId())){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 相对地址转化为url
	 * @param path
	 * @return
	 */
	public String relativePath2Url(String path)
	{
		if (StringUtils.isBlank(path)) {
			return path;
		}
		
		if (!path.toLowerCase().startsWith("http")) {
			String hostUrl =  PropertiesUtil.getServerHostUrl();
			path = hostUrl + path;
		}
		return path;
	}
	
	
	/**
	 * url转化为相对地址
	 * @param path
	 * @return
	 */
	public String url2RelativePath(String url)
	{
		
		if (StringUtils.isBlank(url)) {
			return url;
		}
		
		String hostUrl =  PropertiesUtil.getServerHostUrl();
		
		//如果url地址是本服务器地址
		if (!url.toLowerCase().startsWith(hostUrl)) {
			url = url.replace(hostUrl, "");
		}
		return url;
	}

	/**
	 * 返回该对象的哈希码值
	 */
	@Override
	public int hashCode() {
		
		int result = 17;
		
		result = result * 31 + (getClass().getName().hashCode());
		result = result * 31 + (getId()==null?0:getId().hashCode());
		
		return result;
	}

	/**
	 * 返回该对象的字符串表示(类似数组的toString方法输出结果)
	 */
	@Override
	public String toString() {
		return new StringBuffer(getClass().getName()).append(":ID=").append(getId()==null?"NULL":getId()).toString();
	}
}
