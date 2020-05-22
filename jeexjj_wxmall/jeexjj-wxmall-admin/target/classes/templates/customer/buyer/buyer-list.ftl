<#--
/****************************************************
 * Description: 客户的简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
0 普通用户，1 VIP用户，2 高级VIP用户
-->
<#include "/templates/xjj-list.ftl"> 
<@list id=tabId>
	<thead>
		<tr>
			<th><input type="checkbox" class="bscheckall"></th>
	        <th>商店id</th>
	        <th>用户</th>
	        <th>性别</th>
	        <th>生日</th>
	        <th>最后登陆时间</th>
	        <th>客户级别</th>
	        <th>昵称</th>
	        <th>用户手机号码</th>
	        <th>register_ip</th>
	        <th>头像</th>
	        <th>wx_openid</th>
	        <th>注册时间</th>
	        <th>状态</th>
		</tr>
	</thead>
	<tbody>
		<#list page.items?if_exists as item>
		<tr>
			<td>
			<input type="checkbox" class="bscheck" data="id:${item.id}">
			</td>
			<td>
			    ${item.shopId}
			</td>
			<td>
			    ${item.username}
			</td>
			<td>
			    ${item.gender}
			</td>
			<td>
				<#if item.birthday??>   
		             ${item.birthday?string('yyyy-MM-dd')}
		        </#if> 
			</td>
			<td>
				<#if item.lastLoginTime??>   
			    	${item.lastLoginTime?string('yyyy-MM-dd HH:mm:ss')}
		        </#if> 
			</td>
			<td>
			    ${item.userLevel}
			</td>
			<td>
			    ${item.nickname}
			</td>
			<td>
			    ${item.mobile}
			</td>
			<td>
			    ${item.registerIp}
			</td>
			<td>
				<img src="${item.avatar}" width="100px" height="100px"/>
			</td>
			<td>
			    ${item.wxOpenid}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.status}
			</td>
		</tr>
		</#list>
	</tbody>
</@list>