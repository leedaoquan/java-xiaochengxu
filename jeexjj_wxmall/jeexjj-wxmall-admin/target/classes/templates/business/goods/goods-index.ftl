<#--
/****************************************************
 * Description: 商品的列表页面
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
	<@querygroup title='状态'>
				<@select name="query.status@eq@s" list=XJJConstants.COMMON_STATUS_LIST></@select>
		    </@querygroup>
		    <@button type="purple" icon="fa fa-pencil" onclick="XJJ.view('${base}/business/goods/input','编辑商品','${tabId}');">编辑</@button>
-->
<#include "/templates/xjj-index.ftl">
<@navList navs=navArr/>
<style>
.scroll-content
{
	max-height:700px;
}
</style>

<script>

<#--增加商品的js开始-->
//添加一行规格
function addGoodsSpecLine()
{
	var lineData = "<tr><td><input name='goodSpecName' class='form-control'/></td><td><input name='goodSpecVal' class='form-control'/></td><td><input name='goodSpecPic' class='form-control'/></td>";
	lineData+="<td><button type='button' class='btn btn-danger btn-xs' onclick='removeGoodsSpecLine(this);'><i class='ace-icon fa fa-trash-o align-top bigger-120'></i></button></td></tr>";
	
	$("#goodsSpecBody").append(lineData);
}


//删除一行规格
function removeGoodsSpecLine(that)
{
	$(that).parent().parent().remove();
}


//添加一行参数
function addGoodsAttrLine()
{
	var lineData = "<tr><td><input name='goodAttrName' class='form-control'/></td><td><input name='goodAttrVal' class='form-control'/></td>";
	lineData+="<td><button type='button' class='btn btn-danger btn-xs' onclick='removeGoodsAttrLine(this);'><i class='ace-icon fa fa-trash-o align-top bigger-120'></i></button></td></tr>";
	
	$("#goodsAttrBody").append(lineData);
}


//删除一行参数
function removeGoodsAttrLine(that)
{
	$(that).parent().parent().remove();
}

<#--增加商品的js结束-->

<#--更新商品的js开始-->

//添加一行规格
function addGoodsSpec4Update()
{
	var lineData = "<tr><td><input name='goodSpecName' class='form-control'/></td><td><input name='goodSpecVal' class='form-control'/></td><td><input name='goodSpecPic' class='form-control'/></td>";
	lineData+="<td><input type='hidden' name='goodSpecId' value='0'/><button type='button' isnew='true' class='btn btn-danger btn-xs' onclick='removeGoodsSpecLine(this);'><i class='ace-icon fa fa-trash-o align-top bigger-120'></i></button></td></tr>";
	
	$("#goodsSpecBody").append(lineData);
}


//删除一行规格
function removeGoodsSpec4Update(that)
{
	var isnew = $(that).attr("isnew");
	if("true"==isnew)
	{
		$(that).parent().parent().remove();
	}else
	{
		var specId = $(that).prev().val();
		$(that).prev().val(-specId);
		$(that).parent().parent().hide();
	}
}




//添加一行参数
function addGoodsAttrLine4Update()
{
	var lineData = "<tr><td><input name='goodAttrName' class='form-control'/></td><td><input name='goodAttrVal' class='form-control'/></td>";
	lineData+="<td><input type='hidden' name='goodAttrId' value='0'/><button type='button' isnew='true' class='btn btn-danger btn-xs' onclick='removeGoodsAttr4Update(this);'><i class='ace-icon fa fa-trash-o align-top bigger-120'></i></button></td></tr>";
	
	$("#goodsAttrBody").append(lineData);
}


//删除一行参数
function removeGoodsAttr4Update(that)
{
	var isnew = $(that).attr("isnew");
	if("true"==isnew)
	{
		$(that).parent().parent().remove();
	}else
	{
		var attrId = $(that).prev().val();
		$(that).prev().val(-attrId);
		$(that).parent().parent().hide();
	}
}

<#--更新商品的js结束-->
</script>

<div class="row">
	<div class="col-xs-2 col-sm-2" style="padding-right: 0px;">
		<div class="search-area well well-sm">
			<div class="search-filter-header bg-primary">
				<h5 class="smaller no-margin-bottom">
					<i class="ace-icon fa fa-sliders light-green bigger-130"></i>&nbsp; 按类目查询
				</h5>
			</div>
	
			<div class="space-10"></div>
			<h4 class="blue smaller">
				<i class="fa fa-tags"></i>
				商品类目
			</h4>
			
			<div class="panel panel-primary">
		      <div class="panel-body" style="padding: 0px;">
	        	  <ul id="cat-tree" class="ztree">
					
				  </ul>
		      </div>
		    </div>
			
		<div class="hr hr-dotted"></div>
		<div class="space-4"></div>
		</div>
	</div>
	<div class="col-xs-10 col-sm-10">
	<@content id=tabId>
		<@query queryUrl="${base}/business/goods/list" id=tabId>
		
			<@querygroup title='类目'>
		    	<input type="hidden" id="${tabId}_categoryId" name="query.categoryId@eq@l"/>
				<input type="search" id="${tabId}_categoryName" class="form-control input-sm" readonly="readonly" onclick="XJJ.msgok('请在左侧选择类目');"/>
		    </@querygroup>
		    
			<@querygroup title='名称'>
				<input type="search" id="${tabId}_name" name="query.name@lk@s" class="form-control input-sm" placeholder="请输入名称" aria-controls="dynamic-table">
		    </@querygroup>
		    
		    <@querygroup title='在售'>
				<@select name="query.isOnSale@eq@i" list=XJJConstants.COMMON_SIMPLE_YESNO></@select>
	    	</@querygroup>
		    <@querygroup title='热卖'>
				<@select name="query.isHot@eq@i" list=XJJConstants.COMMON_SIMPLE_YESNO></@select>
	    	</@querygroup>
		    
			<@button type="info" icon="glyphicon glyphicon-search" onclick="XJJ.query({id:'${tabId}'});">查询</@button>
		</@query>
		
		
		<@button type="info" icon="glyphicon glyphicon-plus" onclick="XJJ.add('${base}/business/goods/input','添加商品','${tabId}');">增加</@button>
		<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/goods/delete','删除商品？',true,{id:'${tabId}'});">删除</@button>
		<@button type="info" icon="glyphicon glyphicon-arrow-up" onclick="XJJ.del('${base}/business/goods/onSale','确认上架商品？',true,{id:'${tabId}'});">上架</@button>
		<@button type="info" icon="glyphicon glyphicon-arrow-down" onclick="XJJ.del('${base}/business/goods/cancleSale','确认下架商品？',true,{id:'${tabId}'});">下架</@button>
		
	</@content>
	</div>
</div>

<#--ztree相关-->
<link rel="stylesheet" href="${base}/assets/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base}/assets/ztree/js/jquery.ztree.core.min.js"></script>

 <SCRIPT LANGUAGE="JavaScript">
   var zTreeObj;
   
   var setting = {
		callback: {
			onClick: zTreeOnClick
		}
   };
   
   var zNodes = [
   	  {
   	  		id:-1,
	      	name:"全部",
	      	icon:"${base}/assets/ztree/css/zTreeStyle/img/diy/1_open.png",
	      	checked:true,
	      	open:true
	  }
      <#list categoryList as c>
      	,
	    {
	    	id:${c.id},
	      	name:"${c.name}",
	      	iconOpen:"${base}/assets/ztree/css/zTreeStyle/img/diy/c_open.png",
	      	iconClose:"${base}/assets/ztree/css/zTreeStyle/img/diy/c_close.png",
	      	
	      	open:<#if c_index lt 1>true,<#else>false,</#if>
	   	  	children:[
		      <#if c.subCategoryList?? && c.subCategoryList?size gt 0>
		      <#list c.subCategoryList as cc>
		      	<#if cc_index!=0>,</#if>
		      	{id:${cc.id},name:"${cc.name}"} 
		      </#list>
		      </#if>
		     ]
		 }
      </#list>
   ];
   $(function(){
      zTreeObj = $.fn.zTree.init($("#cat-tree"), setting, zNodes);
   });
   
   //单击节点回调函数
   function zTreeOnClick(event, treeId, treeNode) {
   	   
   	   	$("#${tabId}_categoryName").val(treeNode.name);
   	   //如果是全部
   	   if(-1==treeNode.id)
   	   {
   	   		$("#${tabId}_categoryId").val("");
   	   }else
   	   {
   	   		$("#${tabId}_categoryId").val(treeNode.id);
   	   }
   	   XJJ.query({id:'${tabId}'});
   };
  </SCRIPT>