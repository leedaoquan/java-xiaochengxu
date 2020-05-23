<#--
/****************************************************
 * Description: t_business_product的简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/product/delete/${item.id}','删除t_business_product？',false,{id:'${tabId}'});">删除</@button>
-->
<#include "/templates/xjj-list.ftl"> 
<@list id=tabId>
	<thead>
		<tr>
			<th><input type="checkbox" class="bscheckall"></th>
	        <th>商品</th>
	        <th>规格</th>
	        <th>库存</th>
	        <th>价格</th>
	        <th>主图</th>
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
			    ${item.goodsName}
			</td>
			<td>
			    ${item.goodsSpecificationNames}
			</td>
			<td>
			    ${item.goodsNumber}
			</td>
			<td>
			    ${item.retailPrice}
			</td>
			<td>
			    <img src="${item.url}" height="100px">
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/product/input/${item.id}','编辑SKU商品','${tabId}');">编辑</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>