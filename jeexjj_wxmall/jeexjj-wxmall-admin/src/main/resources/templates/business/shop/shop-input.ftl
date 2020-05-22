<#--
/****************************************************
 * Description: 商家的输入页面，包括添加和修改
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

<@input url="${base}/business/shop/save" id=tabId>
   <input type="hidden" name="id" value="${shop.id}"/>
   
   <@formgroup title='账号'>
	<input type="text" name="identify" value="${shop.identify}" >
   </@formgroup>
   <@formgroup title='名称'>
	<input type="text" name="name" value="${shop.name}" check-type="required">
   </@formgroup>
   <@formgroup title='密码'>
	<input type="password" name="password" value="${shop.password}" check-type="required">
   </@formgroup>
   <@formgroup title='手机'>
	<input type="text" name="mobile" value="${shop.mobile}" >
   </@formgroup>
   <@formgroup title='邮箱'>
	<input type="text" name="email" value="${shop.email}" >
   </@formgroup>
   
   <@formgroup title='状态'>
		<@swichButton name="status" title="有效无效" val=shop.status></@swichButton>
   </@formgroup>
   
   
   <@formgroup title='商家附件'>
		<@upload id="shop" uploadPath="/shop"></@upload>
   </@formgroup>
   <@formgroup title='商家介绍'>
		<textarea name="desc" class="editor"></textarea>
   </@formgroup>
</@input>