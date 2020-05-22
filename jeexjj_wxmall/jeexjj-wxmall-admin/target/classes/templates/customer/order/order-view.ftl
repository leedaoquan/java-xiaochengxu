<#--
/****************************************************
 * Description: 订单的输入页面，包括添加和修改
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

<@input url="" id=tabId>

   <div class="panel panel-success">
      <div class="panel-heading">
        <h3 class="panel-title">客户信息</h3>
      </div>
      <div class="panel-body">
	       <@formgroup title='客户'>
			<input type="text" value="${order.customerName}">
		   </@formgroup>
	       <@formgroup title='手机'>
			<input type="text" value="${order.mobile}">
		   </@formgroup>
		   <@formgroup title='收货地址'>
			<input type="text" value="${order.address}">
		   </@formgroup>
      </div>
   </div>
   <div class="panel panel-success">
      <div class="panel-heading">
        <h3 class="panel-title">订单信息</h3>
      </div>
      <div class="panel-body">
       	<@formgroup title='order_sn'>
			<input type="text" name="orderSn" value="${order.orderSn}">
	   	</@formgroup>
	   	<@formgroup title='订单状态'>
			<input type="text" name="orderStatus" value="${order.orderStatus}">
	   	</@formgroup>
      </div>
   </div>
   
   <div class="panel panel-info">
      <div class="panel-heading">
        <h3 class="panel-title">商品信息</h3>
      </div>
      <div class="panel-body">
      
      		<table class="table">
		        <thead>
		          <tr>
		            <th>商品</th>
		            <th>金额</th>
		            <th>图片</th>
		          </tr>
		        </thead>
		        <tbody id="goodsAttrBody">
		            <#list goodsList as goods>
		            <tr>
		            	<td>
		            	名称：${goods.goodsName}</br>
		            	规格：${goods.goodsSpecVals}
		            	</td>
		            	<td>
		            		单价：${goods.price}元</br>
		            		数量：${goods.number}
		            	</td>
		            	<td>
		            		<img src="${goods.picUrl}" height="100px"/>
		            	</td>
		            </tr>
		            </#list>
		        </tbody>
		    </table> 
      </div>
   </div>

</@input>