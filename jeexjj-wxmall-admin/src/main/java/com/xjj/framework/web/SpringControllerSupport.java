package com.xjj.framework.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xjj.framework.Constants;
import com.xjj.framework.utils.StringUtils;
import com.xjj.wxmall.business.category.service.CategoryService;
import com.xjj.wxmall.business.category.entity.CategoryEntity;
public class SpringControllerSupport {
protected final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private CategoryService categoryService;
	
	private String basePath = "";
	
	/**
	 * 实例化时把类注解的地址赋予basePath
	 */
	public SpringControllerSupport(){
		RequestMapping annotation = getClass().getAnnotation(RequestMapping.class);
		
		if(annotation != null && annotation.value() != null && annotation.value().length > 0){
			this.basePath = annotation.value()[0];
		}
		
		for (int i = 0; i < annotation.value().length; i++) {
			System.out.println("====="+annotation.value()[i]);
		}
	}
	
	
	/**
	 * 查询并拼接好类目
	 * @return
	 */
	public List<CategoryEntity> findCategoryList()
	{
		
		//查询第一级类目
		List<CategoryEntity> categoryList1 = categoryService.findListByLevel1();
		//第二级类目
		List<CategoryEntity> categoryList2 = categoryService.findListByProperty("level","L2");
		//一级类目索引
		Map<Long,Integer> categoryIndex = new HashMap<Long,Integer>();
		if(null!=categoryList1)
		{
			for (int i = 0; i < categoryList1.size(); i++) {
				categoryIndex.put(categoryList1.get(i).getId(),i);
			}
		}
		
		//循环二级类目，并把它塞到一级类目的subCategoryList中
		if(null!=categoryList2)
		{
			int idx = 0;
			for (int i = 0; i < categoryList2.size(); i++) {
				idx = categoryIndex.get(categoryList2.get(i).getParentId());
				categoryList1.get(idx).addSubCategory(categoryList2.get(i));
			}
		}
		return categoryList1;
	}
	
	protected String getViewPath(String path){
		
		StringBuilder builder = new StringBuilder();
		builder.append(basePath).append("/");
		String className = getClass().getSimpleName();
		if(className.endsWith("Controller") && className.length() > 10){
			className = className.substring(0, className.length() - 10);
		}
		builder.append(StringUtils.toUnderScoreCase(className,"-")).append("-");
		if(path.startsWith("/")){
			builder.append(path.substring(1));
		}else{
			builder.append(path);
		}
		return builder.toString(); 
	}
	
	/**
	 * 得到管理员信息
	 * @return
	 */
	public ManagerInfo getManagerInfo() {
		return (ManagerInfo)getRequest().getSession().getAttribute(Constants.SESSION_MANAGER_INFO_KEY);
	}

	public HttpServletRequest getRequest()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

}
