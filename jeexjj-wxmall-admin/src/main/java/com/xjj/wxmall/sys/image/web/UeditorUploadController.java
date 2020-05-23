/****************************************************
 * Description: Controller for t_sys_image_info
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-11-11 zhanghejie Create File
**************************************************/
package com.xjj.wxmall.sys.image.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;
import com.xjj.framework.security.annotations.SecPrivilege;
import com.xjj.framework.utils.FileUtils;
import com.xjj.framework.web.SpringControllerSupport;
import com.xjj.wxmall.sys.image.service.ImageInfoService;

@Controller
@PropertySource("classpath:/application.properties")
@RequestMapping("/sys/image/ueditor")
public class UeditorUploadController extends SpringControllerSupport {
	@Autowired
	private ImageInfoService imageInfoService;

	private static String relativePath = FileUtils.FILE_UPLOAD_PATH + "/images/";

	// ConfigUtils.get("path.PROJECT_ROOT")
	@Value("${spring.resources.static-locations}")
	private String STATIC_LOCATION;

	@RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
 
        response.setContentType("application/json");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");
 
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }

	
}
