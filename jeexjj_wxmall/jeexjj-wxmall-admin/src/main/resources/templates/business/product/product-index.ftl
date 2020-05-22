<#--
/****************************************************
 * Description: t_business_product的列表页面
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/
		<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/product/input','修改t_business_product','${tabId}');">修改</@button>
-->
<#include "/templates/xjj-index.ftl">
<@navList navs=navArr/>
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
	        	  <ul id="cat-tree${tabId}" class="ztree">
					
				  </ul>
		      </div>
		    </div>
			
		<div class="hr hr-dotted"></div>
		<div class="space-4"></div>
		</div>
	</div>
	<div class="col-xs-10 col-sm-10">
	<@content id=tabId>
		<@query queryUrl="${base}/business/product/list" id=tabId>
		
			<@querygroup title='类目'>
				<input type="hidden" id="${tabId}_categoryId" name="categoryId"/>
				<input type="search" id="${tabId}_categoryName" class="form-control input-sm" readonly="readonly" onclick="XJJ.msgok('请在左侧选择类目');"/>
		    </@querygroup>
			<@querygroup title='商品名称'>
				<input type="search" name="goodsName" class="form-control input-sm" placeholder="请输入商品名称" aria-controls="dynamic-table">
		    </@querygroup>
		  	    
			<@button type="info" icon="glyphicon glyphicon-search" onclick="XJJ.query({id:'${tabId}'});">查询</@button>
		</@query>
		
		<@button type="info" icon="glyphicon glyphicon-plus" onclick="addSku();">增加</@button>
		<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/product/delete','确认删除选中的SKU？',true,{id:'${tabId}'});">删除</@button>
	</@content>
	</div>
</div>

<#--ztree相关-->
<link rel="stylesheet" href="${base}/assets/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base}/assets/ztree/js/jquery.ztree.core.min.js"></script>

 <SCRIPT LANGUAGE="JavaScript">
   function addSku()
   {
   		var categoryId = $("#${tabId}_categoryId").val();
   		if(null==categoryId || ""==categoryId)
   		{
   			XJJ.msger("请在左侧类目树中先选择一下sku所属的类目");
   			return ;
   		}
   		XJJ.add('${base}/business/product/'+categoryId+'/input','添加SKU','${tabId}');
   }
   function skuChangeGoods(goodsId)
   {
   		$.ajax({
            type:"GET",
            url:"${base}/business/product/getGoodsSpec/"+goodsId,
            success:function(html){
            	$("#addSkuSpecSpan").html(html);
            },
            error:function(){
            }
        });
   }
   
   <#--------类目树开始-------->
   var zTreeObj${tabId};
   
   var setting${tabId} = {
		callback: {
			onClick: zTreeOnClick${tabId}
		}
   };
   
   var zNodes${tabId} = [
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
      zTreeObj${tabId} = $.fn.zTree.init($("#cat-tree${tabId}"), setting${tabId}, zNodes${tabId});
   });
   
   //单击节点回调函数
   function zTreeOnClick${tabId}(event, treeId, treeNode) {
   	   
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
   <#--------类目树结束-------->
  </SCRIPT>