<#--
/****************************************************
 * Description: 广告的输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
-->
<#include "/templates/xjj-index.ftl"> 

<@input url="${base}/business/ad/save" id=tabId>
   <input type="hidden" name="shopId" value="3"/>
   <input type="hidden" name="id" value="${ad.id}"/>
   
   <@formgroup title='地点'>
	<input type="text" name="position" value="${ad.position}" check-type="required number">
   </@formgroup>
   <@formgroup title='名称'>
	<input type="text" name="name" value="${ad.name}" check-type="required">
   </@formgroup>
   <@formgroup title='链接'>
	<input type="text" name="link" value="${ad.link}" check-type="required">
   </@formgroup>
   <@formgroup title='主图'>
	<@chooseImg name="url" cbId="adPicUrl" defaultValue="${ad.url}"/>
   </@formgroup>
   <@formgroup title='内容'>
	<input type="text" name="content" value="${ad.content}" check-type="required">
   </@formgroup>
   <@formgroup title='开始时间'>
	<@datetime name="startTime" dateValue=ad.startTime  default=true/>
   </@formgroup>
   <@formgroup title='结束时间'>
	<@datetime name="endTime" dateValue=ad.endTime  default=true/>
   </@formgroup>
   <@formgroup title='状态'>
	<@swichButton name="status" val=ad.status></@swichButton>
   </@formgroup>
</@input>