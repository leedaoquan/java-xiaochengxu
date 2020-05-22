<#--
/****************************************************
 * Description: 品牌表的输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

  <@formgroup title='new_pic_url'>
	<input type="text" name="newPicUrl" value="${brand.newPicUrl}" check-type="required">
   </@formgroup>
   <@formgroup title='new_sort_order'>
	<input type="text" name="newSortOrder" value="${brand.newSortOrder}" check-type="required number">
   </@formgroup>
   <input type="text" name="appListPicUrl" value="${brand.appListPicUrl}">
-->
<#include "/templates/xjj-index.ftl"> 

<@input url="${base}/business/brand/save" id=tabId>
   <input type="hidden" name="id" value="${brand.id}"/>
   <input type="hidden" name="shopId" value="${brand.shopId}">
   
   <@formgroup title='名称'>
		<input type="text" name="name" value="${brand.name}" check-type="required">
   </@formgroup>
   <@formgroup title='宣传图片'>
   		<@chooseImg name="listPicUrl" cbId="brandListPicUrl" defaultValue="${brand.listPicUrl}"/>
   </@formgroup>
   <@formgroup title='介绍'>
	<input type="text" name="simpleDesc" value="${brand.simpleDesc}" check-type="required">
   </@formgroup>
   <@formgroup title='品牌商图片'>
		<@chooseImg name="picUrl" cbId="brandpicUrl" defaultValue="${brand.picUrl}"/>
   </@formgroup>
   <@formgroup title='排序号'>
	<input type="text" name="sortOrder" value="${brand.sortOrder}" check-type="required number">
   </@formgroup>
   <@formgroup title='是否新的'>
	<@swichButton name="isNewly" title="是否" val=brand.isNewly val="1" onVal="1" offVal="0"></@swichButton>
   </@formgroup>
   <@formgroup title='是否显示'>
	<@swichButton name="isShow" title="是否" val=brand.isShow val="1" onVal="1" offVal="0"></@swichButton>
   </@formgroup>
   <@formgroup title='底价'>
	<input type="text" name="floorPrice" value="${brand.floorPrice}" check-type="required">
   </@formgroup>
   <@formgroup title='APP宣传图片'>
	<@chooseImg name="appListPicUrl" cbId="brandAppListPicUrl" defaultValue="${brand.appListPicUrl}"/>
   </@formgroup>
   
   <@formgroup title='状态'>
  		<@swichButton name="status" title="有效无效" val=brand.status></@swichButton>
   </@formgroup>
</@input>