<#--
<#include "/templates/xjj-index.ftl">
<@navList navs=navArr/>

<@content id=tabId>
	<@query queryUrl="${base}/business/category/list" id=tabId>
	
		<@querygroup title='名称'>
			<input type="search" name="query.name@lk@s" class="form-control input-sm" placeholder="请输入名称" aria-controls="dynamic-table">
	    </@querygroup>
	  	    
		<@querygroup title='状态'>
			<@select name="query.status@eq@s" list=XJJConstants.COMMON_STATUS_LIST></@select>
	    </@querygroup>
	    
		<@button type="info" icon="glyphicon glyphicon-search" onclick="XJJ.query({id:'${tabId}'});">查询</@button>
	</@query>
	
	
	<@button type="info" icon="glyphicon glyphicon-plus" onclick="XJJ.add('${base}/business/category/input','添加类目','${tabId}');">增加</@button>
	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/category/input','修改类目','${tabId}');">修改</@button>
	<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/category/delete','删除类目？',true,{id:'${tabId}'});">删除</@button>
	
	<@button type="grey" icon="fa fa-cloud-upload">上传</@button>
</@content>

-->

<#include "/templates/xjj-index.ftl">
<@navList navs=navArr/>
<script type="text/javascript">
 function toggle(id,obj){
		$("."+id).toggle();
		var innerH = $("#a"+id).html();
		if(innerH.indexOf('glyphicon-plus') > 0){
			$("#a"+id).html('<span class="glyphicon glyphicon-minus"></span>');
		}else{
			$("#a"+id).html('<span class="glyphicon glyphicon-plus"></span>');
			// 子的全部收起来
			$("."+id+" a").each(function(){
		     var childInnerH = $(this).html();
		     if(childInnerH.indexOf('glyphicon-minus') > 0){
		     	 $(this).trigger("click");
		     }
		  });
		}
	}
</script>

<@content id=tabId>
	<form id="${tabId}queryForm" action="${base}/business/category/list" method="POST"></form>
	<@button type="info" icon="glyphicon glyphicon-plus" onclick="XJJ.add('${base}/business/category/0/input','添加一级类目','${tabId}');">添加一级类目</@button>
</@content>
