<#--
/****************************************************
 * Description: t_sys_image_info的列表页面
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-11-11 zhanghejie Create File
**************************************************/
	<@button type="info" icon="glyphicon glyphicon-plus" onclick="XJJ.add('${base}/sys/image/info/input','添加t_sys_image_info','${tabId}');">增加</@button>
-->
<#include "/templates/xjj-index.ftl">

<@navList navs=navArr/>

<@content id=tabId>
	<@query queryUrl="${base}/sys/image/info/list" id=tabId>
	
		<@querygroup title='名称'>
			<input type="search" name="query.imgTitle@lk@s" class="form-control input-sm" placeholder="请输入名称" aria-controls="dynamic-table">
	    </@querygroup>
		<@querygroup title='标签'>
			<input type="search" name="query.imgKeywords@lk@s" class="form-control input-sm" placeholder="请输入标签" aria-controls="dynamic-table">
	    </@querygroup>
	  	    
		<@button type="info" icon="glyphicon glyphicon-search" onclick="XJJ.query({id:'${tabId}'});">查询</@button>
	</@query>
	
	
	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/sys/image/info/input','修改图片信息','${tabId}');">修改</@button>
	<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/sys/image/info/delete','删除图片信息？',true,{id:'${tabId}'});">删除</@button>
</@content>