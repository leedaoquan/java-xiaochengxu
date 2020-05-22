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

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xjj.framework.json.XjjJson;
import com.xjj.framework.security.annotations.SecPrivilege;
import com.xjj.framework.utils.DateTimeUtils;
import com.xjj.framework.utils.FileUtils;
import com.xjj.framework.utils.StringUtils;
import com.xjj.framework.web.SpringControllerSupport;
import com.xjj.wxmall.sys.image.entity.ImageInfoEntity;
import com.xjj.wxmall.sys.image.service.ImageInfoService;

@Controller
@RequestMapping("/sys/image/upload")
@PropertySource("classpath:/application.properties")
public class ImageUploadController extends SpringControllerSupport {
	@Autowired
	private ImageInfoService imageInfoService;

	private static String relativePath = FileUtils.FILE_UPLOAD_PATH + "/images/";

	// ConfigUtils.get("path.PROJECT_ROOT")
	@Value("${spring.resources.static-locations}")
	private String STATIC_LOCATION;

	@SecPrivilege(title = "图片上传中心")
	@RequestMapping(value = "/index")
	public String index(Model model) {
		String page = this.getViewPath("index");
		return page;
	}

	@RequestMapping(value = "/page")
	public String page(Model model) {
		String page = this.getViewPath("page");
		return page;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody XjjJson upload(@RequestParam("file") MultipartFile files, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// org.json.JSONObject json=new JSONObject();
		response.setCharacterEncoding("utf-8");
		System.out.println("-------------------开始调用上传文件upload接口-------------------");
		try {
			String oldName = files.getOriginalFilename();
			System.out.println("files.getOriginalFilename()==" + files.getOriginalFilename());

			if (StringUtils.isBlank(STATIC_LOCATION)) {
				throw new IOException(
						"请配置静态文件路径application.properties的spring.resources.static-locations。Example：spring.resources.static-locations=file:/projecPah/WEB-INF/classes/static");
			}

			String yearMonthDay = DateTimeUtils.format(DateTimeUtils.getCurrentDate(), "yyyyMMdd");

			// =============================写死路径上传开始==============================
			String staticLocation = null;
			if (STATIC_LOCATION.startsWith("file:")) {
				staticLocation = STATIC_LOCATION.substring(5);
			}
			String newName = getNewlyName(oldName);
			String imgRelativePath = relativePath + yearMonthDay + "/";
			FileUtils.createFolder(staticLocation + imgRelativePath);
			String fileFullPath = staticLocation + imgRelativePath + newName;
			System.out.println("上传的路径============" + fileFullPath);
			File uploadFile = new File(fileFullPath);
			files.transferTo(uploadFile);
			// =============================写死路径上传结束==============================

			// =============================动态获取路径上传开始==============================
			// String uploadPath =
			// FileUtils.getUploadRealPath("/images/"+yearMonthDay +"/");
			// String imgRelativePath = relativePath+yearMonthDay+"/";
			// String fileFullPath = uploadPath + name;
			// File uploadFile = new File(fileFullPath);
			// System.out.println("上传的路径============" + fileFullPath);
			// files.transferTo(uploadFile);
			// =============================动态获取路径上传结束==============================

			// 保存图片数据信息
			ImageInfoEntity image = new ImageInfoEntity();
			image.setImgPath(fileFullPath);
			image.setImgUrl(imgRelativePath + newName);
			image.setImgTitle(oldName);
			image.setImgSize((int) uploadFile.length());
			image.setCreateDate(DateTimeUtils.getCurrentDate());
			image.setUserId(this.getManagerInfo().getUserId());
			imageInfoService.save(image);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-------------------结束调用上传文件upload接口-------------------");
		return XjjJson.success("111");
	}

	/**
	 * 生成新的唯一名字
	 * 
	 * @param oldName
	 * @return
	 * @throws Exception
	 */
	private static String getNewlyName(String oldName) throws Exception {

		int idx = oldName.lastIndexOf(".");
		if (idx < 0) {
			throw new IOException("upload file " + oldName + " error,becase not have extension name.");
		}
		String extensionName = oldName.substring(idx);
		int fileRandom = Double.valueOf(Math.random() * 100).intValue();
		String newlyName = DateTimeUtils.getCurrentDateTimeString("yyyyMMddHHmmsss") + fileRandom + extensionName;

		return newlyName;
	}
	
	
	public static void main(String[] args) throws Exception {
		String name = getNewlyName("aaa.aaa.jpg");
		System.out.println(name);
	}
}
