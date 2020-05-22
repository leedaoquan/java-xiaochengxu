package com.xjj.wxmall.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.xjj.framework.utils.StringUtils;

public class PropertiesUtil {
	
	private static Properties properties =  null;
	private static String SERVER_HOST_URL = null;
	
	/**
	 * 获得属性配置
	 * @param key
	 * @return
	 */
	public static String getProperty(String key)
	{
		if(null==properties)
		{
			try {
				properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
			} catch (IOException e) {
				
				return null;
			}
		}
		
		if(null==properties)
		{
			return null;
		}
		return properties.getProperty(key);
	}
	
	/**
	 * 获得属性配置
	 * @param key
	 * @return Integer
	 */
	public static Integer getIntProperty(String key)
	{
		String val = getProperty(key);
		if(StringUtils.isBlank(val))
		{
			return null;
		}
		return Integer.parseInt(val);
	}
	
	/**
	 * 获得主机地址
	 * @return
	 */
	public static String getServerHostUrl()
	{
		if(null==SERVER_HOST_URL)
		{
			String host = PropertiesUtil.getProperty("server.host");
			int port = PropertiesUtil.getIntProperty("server.port");
			String contextPath = PropertiesUtil.getProperty("server.context-path");
			SERVER_HOST_URL = host + ":" + port;
			if(contextPath.length()>1)
			{
				SERVER_HOST_URL += contextPath;
			}
		}
		
		return SERVER_HOST_URL;
	}
	
}
