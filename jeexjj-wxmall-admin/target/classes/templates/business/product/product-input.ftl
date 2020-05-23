<#--
/****************************************************
 * Description: t_business_product的输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/

<@formgroup title='goods_id'>
	<input type="text" name="goodsId" value="${product.goodsId}" check-type="required number">
   </@formgroup>
   <@formgroup title='goods_specification_ids'>
	<input type="text" name="goodsSpecificationIds" value="${product.goodsSpecificationIds}" check-type="required">
   </@formgroup>
-->
<#include "/templates/xjj-index.ftl"> 

<@input url="${base}/business/product/save" id=tabId>
   <input type="hidden" name="id" value="${product.id}"/>
   <@formgroup title='类目'>
   <input readonly="" type="text" class="col-xs-10 col-sm-5" id="form-input-readonly" value="${category.name}" />
   </@formgroup>
   
   <@formgroup title='商品 '>
	<@select name="goodsId" list=goodsList listKey='name' listValue='id' onChange="skuChangeGoods(this.value);"></@select>
   </@formgroup>
   
   <@formgroup title='规格'>
	<span id="addSkuSpecSpan"><font color="red">选择商品后，会加载规格信息</font></span>
   </@formgroup>
   
   <@formgroup title='库存'>
	<input type="text" name="goodsNumber" value="${product.goodsNumber}" check-type="required number">
   </@formgroup>
   <@formgroup title='价格'>
	<input type="text" name="retailPrice" value="${product.retailPrice}" check-type="required">
   </@formgroup>
   <@formgroup title='图片'>
		<@chooseImg name="url" cbId="skuImgUrl" defaultValue="${product.url}"/>
   </@formgroup>
</@input>