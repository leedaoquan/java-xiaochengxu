<#--
/****************************************************
 * Description: t_sys_image_info的输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-11-11 zhanghejie Create File
      <@formgroup title='创建人'>
	<input type="text" name="userId" value="${imageInfo.userId}" check-type="number">
   </@formgroup>
   <@formgroup title='扩展名'>
	<input type="text" name="extensionName" value="${imageInfo.extensionName}" >
   </@formgroup>
   <@formgroup title='创建时间'>
	<@date name="createDate" dateValue=imageInfo.createDate required="required" default=true/>
   </@formgroup>
    <@formgroup title='路径'>
	<input type="text" name="imgPath" value="${imageInfo.imgPath}" check-type="required">
   </@formgroup>
   <@formgroup title='图片大小'>
	<input type="text" name="imgSize" value="${imageInfo.imgSize}" check-type="number">
   </@formgroup>
**************************************************/
-->
<#include "/templates/xjj-index.ftl"> 

<@input url="${base}/sys/image/info/save" id=tabId>
   <input type="hidden" name="id" value="${imageInfo.id}"/>	
   <@formgroup title='图片'>
   <div style="width:600px;overflow:scroll"">
	<img src="${imageInfo.imgUrl}"/>
   </div>
   </@formgroup>
   <@formgroup title='标题'>
	<input type="text" name="imgTitle" value="${imageInfo.imgTitle}" readonly="readonly">
   </@formgroup>
   <@formgroup title='标签'>
   
   	 <#if imageInfo.imgKeywords??>
	    <#list imageInfo.imgKeywords as key>
	    <span>
	    	<span>
	    		<span class="label label-primary">
	    		${key}
	    		</span>
	    	</span>
	    </span>
	    </#list>
	    <br/><br/>
	 </#if>
   </@formgroup>
   
   <@formgroup title='宽度'>
	<input type="text" name="imgWidth" value="${imageInfo.imgWidth}" readonly="readonly">
   </@formgroup>
   <@formgroup title='高度'>
	<input type="text" name="imgHeight" value="${imageInfo.imgHeight}" readonly="readonly">
   </@formgroup>
 
</@input>