<#--
/****************************************************
 * Description: t_sys_image_info的简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-11-11 zhanghejie Create File
**************************************************/
<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/sys/image/info/delete/${item.id}','删除图片信息？',false,{id:'${tabId}'});">删除</@button>
-->
<#include "/templates/xjj-list.ftl"> 
<@list id=tabId>
	<thead>
		<tr>
			<th><input type="checkbox" class="bscheckall"></th>
	        <th>图片信息</th>
	        <th>图片</th>
	        <th>创建信息</th>
	        <th>操作</th>
		</tr>
	</thead>
	<tbody>
		<#list page.items?if_exists as item>
		<tr>
			<td>
			<input type="checkbox" class="bscheck" data="id:${item.id}">
			</td>
			<td>
			    名称：${item.imgTitle}<br/>
			    标签：
			    <#if item.imgKeywords??>
			    <#list item.imgKeywords as key>
			    <span class="label label-primary">${key}</span>
			    </#list>
			    </#if><br/>
			    大小：${item.imgSize/1024.0}KB<br/>
			    地址：${item.imgUrl}<br/>
			</td>
			<td>
			    <img src="${item.imgUrl}" height="100" style="cursor:pointer;" onclick="XjjUtil.openWidow('${item.imgUrl}','查看图片')"/>
			</td>
			<td>
			   	创建人：${item.userId}<br/>
			    创建时间：${item.createDate?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			
			<td>
				<@button type="info" icon="fa fa-eye" onclick="XJJ.view('${base}/sys/image/info/view/${item.id}','查看图片信息');">查看</@button>
			</td>
		</tr>
		</#list>
	</tbody>
</@list>