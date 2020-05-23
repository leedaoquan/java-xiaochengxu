<#--
/****************************************************
 * Description: 商品的输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

<form id="goodsUpdateForm" action="${base}/business/goods/update" method="post">
 <@button type="primary" icon="glyphicon glyphicon-floppy-disk" onclick="updateGoodsSpecAjax(${goods.id});" bigger="118">保存</@button>
        
-->
<#include "/templates/xjj-index.ftl"> 
<@input url="${base}/business/goods/update" id=tabId>
   <div class="panel panel-primary">
      <div class="panel-heading">
        <h3 class="panel-title">基础信息</h3>
      </div>
      <div class="panel-body">
      	   <input type="hidden" name="id" value="${goods.id}"/>
	       <@formgroup title='品牌'>
			<@select name="brandId" value="${goods.brandId}" list=brandList listKey='name' listValue='id'/>
		   </@formgroup>
		   <@formgroup title='类目'>
			<select name="categoryId">
				<#list categoryList as category>
					<option <#if goods.categoryId==category.id>selected="selected"</#if> value="${category.id}">|-${category.name}</option>
					<#if category.subCategoryList??>
						<#list category.subCategoryList as sub>
							<option <#if goods.categoryId==sub.id>selected="selected"</#if> value="${sub.id}">|-------${sub.name}</option>
						</#list>
					</#if>
				</#list>
			</select>
		   </@formgroup>

		   <@formgroup title='名称'>
			<input type="text" name="name" value="${goods.name}" check-type="required">
		   </@formgroup>
		   <@formgroup title='画廊图片' widthdata=9>
		   	<@chooseImgMulti name="gallery" cbId="goodsGallery" defaultList=goods.gallery/>
		   </@formgroup>
		   <@formgroup title='关键字'>
			<input type="text" name="keywords" value="${goods.keywords}" >
		   </@formgroup>
		   <@formgroup title='商品简介'>
			<input type="text" name="goodsBrief" value="${goods.goodsBrief}" >
		   </@formgroup>
		   
		   <@formgroup title='排序号'>
			<input type="text" name="sortOrder" value="${goods.sortOrder}" check-type="number">
		   </@formgroup>
		   <@formgroup title='专柜价格'>
			<input type="text" name="counterPrice" value="${goods.counterPrice}" >
		   </@formgroup>
		   <@formgroup title='是否新品'>
			<@swichButton name="isNewly" title="是否" val="${goods.isNewly}" onVal="1" offVal="0"></@swichButton>
		   </@formgroup>
		   <@formgroup title='商品主图'>
			<@chooseImg name="primaryPicUrl" cbId="goodsPrimaryPicUrl" defaultValue="${goods.primaryPicUrl}"/>
		   </@formgroup>
		   <@formgroup title='商品列表图'>
			<@chooseImg name="listPicUrl" cbId="goodsListPicUrl" defaultValue="${goods.listPicUrl}"/>
		   </@formgroup>
		   <@formgroup title='是否热卖'>
			<@swichButton name="isHot" title="是否" val="${goods.isHot}" onVal="1" offVal="0"></@swichButton>
		   </@formgroup>
		   <@formgroup title='商品单位'>
			<input type="text" name="goodsUnit" value="${goods.goodsUnit}" >
		   </@formgroup>
		   <@formgroup title='零售价格'>
			<input type="text" name="retailPrice" value="${goods.retailPrice}" >
		   </@formgroup>
		   <@formgroup title='商品详情' widthdata=10>
			<textarea name="goodsDesc" class="editor">
			${goods.goodsDesc}
			</textarea>
		   </@formgroup> 
		   <#--
      	  <@button type="primary" icon="glyphicon glyphicon-floppy-disk" onclick="updateGoodsAjax();" bigger="118">保存</@button>
      	  -->
      </div>
    </div>
    
    <div class="panel panel-success">
      <div class="panel-heading">
        <h3 class="panel-title">规格信息</h3>
      </div>
      <div class="panel-body">
        <table class="table">
	        <thead>
	          <tr>
	            <th>规格名</th>
	            <th>规格值</th>
	            <th>规格图片</th>
	            <th>#</th>
	          </tr>
	        </thead>
	        <tbody id="goodsSpecBody">
	        
              <#list specList as spec>
              	<tr>
              	<td><input name='goodSpecName' value='${spec.specification}'/></td>
              	<td><input name='goodSpecVal' value='${spec.value}'/></td>
              	<td><input name='goodSpecPic' value='${spec.picUrl}'/></td>
              	<td>
              		<input type='hidden' name='goodSpecId' value='${spec.id}'/>
              		<button type='button' isnew='false' class='btn btn-danger btn-xs' onclick='removeGoodsSpec4Update(this);'><i class='ace-icon fa fa-trash-o align-top bigger-120'></i></button>
              	</td>
              	</tr>
              </#list>
	        </tbody>
	    </table>
	    <@button type="success" icon="glyphicon glyphicon-plus" onclick="addGoodsSpec4Update();" bigger="118">添加一行</@button>
      </div>
    </div>
    
    <div class="panel panel-info">
      <div class="panel-heading">
        <h3 class="panel-title">参数信息</h3>
      </div>
      <div class="panel-body">
	        <table class="table">
		        <thead>
		          <tr>
		            <th>参数名</th>
		            <th>参数值</th>
		            <th>#</th>
		          </tr>
		        </thead>
		        <tbody id="goodsAttrBody">
				  <#list attrList as attr>
	              	<tr>
	              	<td><input name='goodAttrName' value='${attr.attribute}'/></td>
	              	<td><input name='goodAttrVal' value='${attr.value}'/></td>
	              	<td>
	              	<input type='hidden' name='goodAttrId' value='${attr.id}'/>
	              	<button type='button' isnew='false' class='btn btn-danger btn-xs' onclick='removeGoodsAttr4Update(this);'><i class='ace-icon fa fa-trash-o align-top bigger-120'></i></button>
	              	</td>
	              	</tr>
	              </#list>
		        </tbody>
		    </table> 
      		<@button type="success" icon="glyphicon glyphicon-plus" onclick="addGoodsAttrLine4Update();" bigger="118">添加一行</@button>
      </div>
    </div>
</@input>