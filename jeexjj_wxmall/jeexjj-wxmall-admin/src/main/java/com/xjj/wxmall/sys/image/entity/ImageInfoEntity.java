/****************************************************
 * Description: Entity for t_sys_image_info
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-11-11 zhanghejie Create File
**************************************************/

package com.xjj.wxmall.sys.image.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ImageInfoEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public ImageInfoEntity(){}
    private String imgPath;//路径
    private String imgTitle;//标题
    private String[] imgKeywords;//关键词
    private String imgUrl;//URL地址
    private Integer imgSize;//图片大小
    private Integer imgWidth;//宽度
    private Integer imgHeight;//高度
    private Long userId;//创建人
    private String extensionName;//扩展名
    private Date createDate;//创建时间
    /**
     * 返回路径
     * @return 路径
     */
    public String getImgPath() {
        return imgPath;
    }
    
    /**
     * 设置路径
     * @param imgPath 路径
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    
    /**
     * 返回标题
     * @return 标题
     */
    public String getImgTitle() {
        return imgTitle;
    }
    
    /**
     * 设置标题
     * @param imgTitle 标题
     */
    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }
    
    /**
     * 返回关键词
     * @return 关键词
     */
    public String[] getImgKeywords() {
        return imgKeywords;
    }
    
    /**
     * 设置关键词
     * @param imgKeywords 关键词
     */
    public void setImgKeywords(String[] imgKeywords) {
        this.imgKeywords = imgKeywords;
    }
    
    /**
     * 返回URL地址
     * @return URL地址
     */
    public String getImgUrl() {
        return relativePath2Url(imgUrl);
    }
    
    /**
     * 设置URL地址
     * @param imgUrl URL地址
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    /**
     * 返回图片大小
     * @return 图片大小
     */
    public Integer getImgSize() {
        return imgSize;
    }
    
    /**
     * 设置图片大小
     * @param imgSize 图片大小
     */
    public void setImgSize(Integer imgSize) {
        this.imgSize = imgSize;
    }
    
    /**
     * 返回宽度
     * @return 宽度
     */
    public Integer getImgWidth() {
        return imgWidth;
    }
    
    /**
     * 设置宽度
     * @param imgWidth 宽度
     */
    public void setImgWidth(Integer imgWidth) {
        this.imgWidth = imgWidth;
    }
    
    /**
     * 返回高度
     * @return 高度
     */
    public Integer getImgHeight() {
        return imgHeight;
    }
    
    /**
     * 设置高度
     * @param imgHeight 高度
     */
    public void setImgHeight(Integer imgHeight) {
        this.imgHeight = imgHeight;
    }
    
    /**
     * 返回创建人
     * @return 创建人
     */
    public Long getUserId() {
        return userId;
    }
    
    /**
     * 设置创建人
     * @param userId 创建人
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    /**
     * 返回扩展名
     * @return 扩展名
     */
    public String getExtensionName() {
        return extensionName;
    }
    
    /**
     * 设置扩展名
     * @param extensionName 扩展名
     */
    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }
    
    /**
     * 返回创建时间
     * @return 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * 设置创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wxmall.sys.image.entity.ImageInfoEntity").append("ID="+this.getId()).toString();
    }
}

