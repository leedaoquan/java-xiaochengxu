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

<@input url="${base}/customer/order/shipped/${order.id}" id=tabId>
   <div class="panel panel-primary">
      <div class="panel-heading">
        <h3 class="panel-title">快递信息</h3>
      </div>
      <div class="panel-body">
      	  <@formgroup title='发货编号'>
			<input type="text" name="shipSn" value="${order.shipSn}" check-type="required">
		   </@formgroup>
		   <@formgroup title='发货快递公司'>
			<input type="text" name="shipChannel" value="${order.shipChannel}" check-type="required">
		   </@formgroup>
      </div>
   </div>
   
   <div class="panel panel-success">
      <div class="panel-heading">
        <h3 class="panel-title">订单信息</h3>
      </div>
      <div class="panel-body">
      	 <@formgroup title='order_sn'>
			<input type="text" name="orderSn" value="${order.orderSn}" readonly="readonly">
		 </@formgroup>
		 <@formgroup title='订单状态'>
			<span class="label label-success arrowed-in arrowed-in-right">${XJJDict.getIntText(order.orderStatus)}</span>
		 </@formgroup>
      </div>
   </div>
   
   <div class="panel panel-success">
      <div class="panel-heading">
        <h3 class="panel-title">客户信息</h3>
      </div>
      <div class="panel-body">
      	   <@formgroup title='consignee'>
			    <input type="text" name="consignee" value="${order.consignee}" readonly="readonly">
		   </@formgroup>
		   <@formgroup title='mobile'>
			    <input type="text" name="mobile" value="${order.mobile}" readonly="readonly">
		   </@formgroup>
		   <@formgroup title='address'>
			    <input type="text" name="address" value="${order.address}" readonly="readonly">
		   </@formgroup>
      </div>
   </div>
   <div class="panel panel-success">
      <div class="panel-heading">
        <h3 class="panel-title">支付信息</h3>
      </div>
      <div class="panel-body">
      	   <@formgroup title='商品总费用'>
			<input type="text" name="goodsPrice" value="${order.goodsPrice}" readonly="readonly">
		   </@formgroup>
		   
		   <@formgroup title='优惠券减免'>
			<input type="text" name="couponPrice" value="${order.couponPrice}" readonly="readonly">
		   </@formgroup>
		   <@formgroup title='用户积分减免'>
			<input type="text" name="integralPrice" value="${order.integralPrice}" readonly="readonly">
		   </@formgroup>
		   <@formgroup title='订单费用'>
			<input type="text" name="orderPrice" value="${order.orderPrice}" readonly="readonly">
		   </@formgroup>
		   <@formgroup title='实付费用'>
			<input type="text" name="actualPrice" value="${order.actualPrice}" readonly="readonly">
		   </@formgroup>
		      <@formgroup title='配送费用'>
			<input type="text" name="freightPrice" value="${order.freightPrice}" readonly="readonly">
		   </@formgroup>
		   
		   <@formgroup title='微信付款编号'>
			<input type="text" name="payId" value="${order.payId}" >
		   </@formgroup>
		   <@formgroup title='支付状态'>
			<input type="text" name="payStatus" value="${order.payStatus}" check-type="number" readonly="readonly">
		   </@formgroup>
		   <@formgroup title='微信付款时间'>
				<#if item.payTime??>
                    ${order.payTime?string('yyyy-MM-dd HH:mm:ss')} <br/>
                </#if>
		   </@formgroup>
      </div>
   </div>
</@input>