<#--
/****************************************************
 * Description: 品牌表的简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/brand/input/${item.id}','修改品牌【商家：${item.shopName}】','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/brand/delete/${item.id}','删除品牌表？',false,{id:'${tabId}'});">删除</@button>
-->
<#include "/templates/xjj-list.ftl"> 
<@list id=tabId>
	<thead>
		<tr>
			<th><input type="checkbox" class="bscheckall" width="3%"></th>
	        <th width="50%">信息</th>
	        <th width="47%">【宣传图片】 【品牌图片】 【app宣传图片】</th>
		</tr>
	</thead>
	<tbody>
		<#list page.items?if_exists as item>
		<tr>
			<td>
				<input type="checkbox" class="bscheck" data="id:${item.id}">
			</td>
		
			<td>
			    【名称】:<b>${item.name}</b>&nbsp;&nbsp;&nbsp;&nbsp;[创建于${item.addTime?string('yyyy-MM-dd HH:mm:ss')}]<br/>
			    【状态】:
			    <span class="label <#if item.status=XJJConstants.COMMON_STATUS_VALID>label-info</#if> arrowed-in arrowed-in-right">${XJJDict.getText(item.status)}</span>
			    <br/>
			    【介绍】:${item.simpleDesc}
			</td>
			<td>
			    <img src="${item.listPicUrl}" height="80px;"/>
			    <img src="${item.picUrl}" height="80px;"/>
			    <img src="${item.appListPicUrl}" height="80px;"/>
			</td>
		</tr>
		</#list>
	</tbody>
</@list>