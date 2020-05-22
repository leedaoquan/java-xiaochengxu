/****************************************************
 * Description: ServiceImpl for t_sys_image_info
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-11-11 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.sys.image.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjj.framework.dao.XjjDAO;
import com.xjj.framework.service.XjjServiceSupport;
import com.xjj.wxmall.sys.image.entity.ImageInfoEntity;
import com.xjj.wxmall.sys.image.dao.ImageInfoDao;
import com.xjj.wxmall.sys.image.service.ImageInfoService;

@Service
public class ImageInfoServiceImpl extends XjjServiceSupport<ImageInfoEntity> implements ImageInfoService {

	@Autowired
	private ImageInfoDao imageInfoDao;

	@Override
	public XjjDAO<ImageInfoEntity> getDao() {
		
		return imageInfoDao;
	}
}