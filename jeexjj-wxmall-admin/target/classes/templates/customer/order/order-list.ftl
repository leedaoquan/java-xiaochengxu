<#--
/****************************************************
 * Description: 订单的简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
支付状态：${item.payStatus}<br/>
-->
<#include "/templates/xjj-list.ftl">
<@list id=tabId>
	<thead>
    <tr>
        <th><input type="checkbox" class="bscheckall"></th>
        <th>客户信息</th>
        <th>订单信息</th>
        <th>费用信息</th>
        <th>支付信息</th>
        <th>发货信息</th>
    </tr>
    </thead>
	<tbody>
		<#list page.items?if_exists as item>
        <tr>
            <td>
                <input type="checkbox" class="bscheck" data="id:${item.id}">
            </td>
            <td>
                客户：${item.customerName}<br/>
                地址：${item.address}<br/>
                收货人：${item.consignee}<br/>
                手机号：${item.mobile}<br/>
                <button class="btn btn-minier btn-info" onclick="XJJ.view('${base}/customer/order/view/${item.id}','订单详情');">
                	<i class="ace-icon fa fa-shopping-cart bigger-160"></i>
            		详情
            	</button>
            	
            	<#if item.orderStatus==201>
                	<button class="btn btn-minier btn-purple" onclick="XJJ.edit('${base}/customer/order/deliver/${item.id}','发货','${tabId}');">
                	<i class="ace-icon fa fa-shopping-cart bigger-160"></i>
                	发货
                	</button>
                </#if>
            </td>
            <td>
                订单状态：
                <#if item.orderStatus==101>
                	<span class="label label-sm label-grey arrowed-in-right arrowed-in">${XJJDict.getIntText(item.orderStatus)}</span>
                </#if>
                
                <#if item.orderStatus==102>
                	<span class="label label-sm arrowed"><s>${XJJDict.getIntText(item.orderStatus)}</s></span>
                </#if>
                <#if item.orderStatus==103>
                	<span class="label label-sm arrowed"><s>${XJJDict.getIntText(item.orderStatus)}</s></span>
                </#if>
                <#if item.orderStatus==201>
                	<span class="label label-sm label-success arrowed-in arrowed-in-right">${XJJDict.getIntText(item.orderStatus)}</span>
                </#if>
                
                <#if item.orderStatus==202>
                	<span class="label label-sm label-warning arrowed arrowed-right">${XJJDict.getIntText(item.orderStatus)}</span>
                </#if>
                
                <#if item.orderStatus==203>
                	<span class="label label-sm label-grey arrowed-in-right arrowed-in"><s>${XJJDict.getIntText(item.orderStatus)}</s></span>
                </#if>
                <#if item.orderStatus==301>
                	<span class="label label-sm label-info arrowed-right arrowed-in">${XJJDict.getIntText(item.orderStatus)}</span>
                </#if>
                <#if item.orderStatus==401>
                	<span class="label label-sm label-primary arrowed arrowed-right">${XJJDict.getIntText(item.orderStatus)}</span>
                </#if>
                <#if item.orderStatus==402>
                	<span class="label label-sm label-danger arrowed">${XJJDict.getIntText(item.orderStatus)}</span>
                </#if>
                <br/>
                订单编号：${item.orderSn}<br/>
                商品费用：${item.goodsPrice}<br/>
                配送费用：${item.freightPrice}<br/>
                下单时间：
			    <#if item.addTime??>
                    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')} <br/>
                </#if>
            </td>
            <td>
                订单费用： ${item.orderPrice}元<br/>
                实付费用： ${item.actualPrice}元<br/>
                优惠减免：${item.couponPrice}元<br/>
                积分减免：${item.integralPrice}元<br/>
            </td>

            <td>
                微信付款编号：${item.payId}<br/>
                微信付款时间：
				<#if item.payTime??>
                    ${item.payTime?string('yyyy-MM-dd')}
                </#if>
            </td>

            <td>
                发货编号：${item.shipSn}<br/>
                快递公司：${item.shipChannel}<br/>
                发货时间：
	        	<#if item.shipStartTime??>
                    ${item.shipStartTime?string('yyyy-MM-dd HH:mm:ss')}
                </#if>
                <br/>
                收货时间：
	        	<#if item.shipEndTime??>
                    ${item.shipEndTime?string('yyyy-MM-dd HH:mm:ss')}
                </#if>
                <br/>
                确认时间：
	        	<#if item.confirmTime??>
                    ${item.confirmTime?string('yyyy-MM-dd HH:mm:ss')}
                </#if>
            </td>

        </tr>
        </#list>
    </tbody>
</@list>