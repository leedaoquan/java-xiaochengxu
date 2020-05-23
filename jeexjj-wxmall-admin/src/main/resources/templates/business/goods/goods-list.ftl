<#--
/****************************************************
 * Description: 商品的简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/goods/delete/${item.id}','删除商品？',false,{id:'${tabId}'});">删除</@button>
            
-->
<#include "/templates/xjj-list.ftl"> 
<@list id=tabId>
	<thead>
		<tr>
			<th><input type="checkbox" class="bscheckall"></th>
	        <th>商品信息</th>
	        <th>价格</th>
	        <th>商品主图及列表图</th>
	        <th>创建时间</th>
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
			    名称：<b>${item.name}</b><br/>
			    类目：${item.categoryName}<br/>
			    品牌：${item.brandName}<br/>
			    简介：${item.goodsBrief}<br/>
			    
			    <#if item.isOnSale==1>
			    	<span class="label label-primary arrowed-right arrowed-in">在售</span>
			    <#else>
			    	<span class="label arrowed-right"><s>下架</s></span>
			    </#if>
			    
			    <#if item.isHot==1>
			    	<span class="label label-danger arrowed-right arrowed-in">热卖</span>
			    </#if>
			    
			</td>
			<td>
			    零售：${item.retailPrice}<br/>
			    专柜：${item.counterPrice}
			</td>
			<td>
			    <img src="${item.primaryPicUrl}" width="100px"/>
			    <img src="${item.listPicUrl}" width="100px"/>
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd')}
			</td>
			<td>
			    <@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/goods/input/${item.id}','编辑商品','${tabId}');">编辑</@button>
			    <@button type="info" icon="glyphicon glyphicon-hdd" onclick="XJJ.edit('${base}/business/product/sku/${item.id}','SKU库存设置','${tabId}');">SKU库存设置</@button>
			</td>
		</tr>
		</#list>
	</tbody>
</@list>