<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Search Results - Ace Admin</title>

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${base}/ace/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${base}/ace/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="${base}/ace/assets/css/select2.min.css" />
		<link rel="stylesheet" href="${base}/ace/assets/css/jquery-ui.custom.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="${base}/ace/assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${base}/ace/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${base}/ace/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="${base}/ace/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="${base}/ace/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${base}/ace/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="${base}/ace/assets/js/ace-extra.min.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="${base}/ace/assets/js/html5shiv.min.js"></script>
		<script src="${base}/ace/assets/js/respond.min.js"></script>
		<![endif]-->
		<style>
			.selected
			{
				border:1px;
				border-style:solid;
  				border-color:blue;
			}
		</style>
	</head>

	<body class="no-skin">
		
	<form id="queryForm" method="post" action="${base}/sys/image/info/select">
	<input type="hidden" name="currentPage" id="currentPage"/>
	<input type="hidden" name="multi" value="${multi}"/>
	<input type="hidden" name="cbId" value="${cbId}"/>
	<div class="col-xs-12 col-sm-12">
		<div class="row">
			<div class="search-area well col-xs-12">
				<div class="col-xs-4 col-sm-4 col-md-4">
				
					<div class="input-group">
				        <div class="input-group-addon">名称：</div>
						<input type="search" name="query.imgTitle@lk@s" class="form-control input-sm" value="${imgTitle}" placeholder="请输入名称">
				    </div>
				
				</div>
				<div class="col-xs-4 col-sm-4 col-md-4">
					<div class="input-group">
				        <div class="input-group-addon">关键字：</div>
						<input type="search" name="query.imgKeywords@lk@s" class="form-control input-sm" value="${imgKeywords}" placeholder="请输入关键字">
				    </div>
				</div>
				
				
				<div class="col-xs-2">
					<button type="button" class="btn btn-info btn-xs" onclick="queryImg(1);">
    					<i class="ace-icon glyphicon glyphicon-search align-top bigger-125"></i>查询
					</button>
				</div>
			</div>
		</div>
	
		<div class="row">
			<#if page.items?size==0>
				<div class="col-xs-12">
				没有查到符合条件的图片！<br/><br/>
				</div>
			</#if>
			<#list page.items?if_exists as item>
			<div class="col-xs-3 col-sm-3 col-md-3">
				<div class="thumbnail" imgId="${item.id}" imgUrl="${item.imgUrl}" title="单击选中" style="cursor:pointer;padding:2px;height:250px;">
					<img class="media-object" src="${item.imgUrl}" style="height:150px;"/>
					<div class="caption">
						<p>${item.imgTitle}</p>
						<div>
						<#if item.imgKeywords??>
					    	<#list item.imgKeywords as key>
					    	<span class="label label-primary">${key}</span>
					    	</#list>
					    <#else>&nbsp;
					    </#if>
						</div>
					</div>
				</div>
			</div>
			</#list>
		</div>
		
		
		<div class="row" style="background:#ecf1f4;">
			<div class="col-xs-2">
				<button class="btn btn-info btn-xs" onclick="confirmImginfo();" style="margin: 20px 0">
					<i class="ace-icon fa fa-check-square-o bigger-160"></i>
					确认选中
				</button>
			</div>
			
			<div class="col-xs-9">
			
				<ul class="pagination">
					<#--------分页标签开始------->
				    <#assign pagenum = page.totalPage>
					<#if pagenum gt 1>
						<li <#if page.currentPage == 1>class="disabled"</#if>>
						
							<#if page.currentPage == 1>
									<span class="glyphicon glyphicon-step-backward"></span>
							<#else>
								<a href="javascript:queryImg(1);" class="crud crudfirst">
									<span class="glyphicon glyphicon-step-backward"></span>
								</a>
							</#if>
						</li>
						<li <#if page.currentPage == 1>class="disabled"</#if>>
						
							<#if page.currentPage gt 1>
								<a href="javascript:queryImg(${page.currentPage-1});" class="crud crudprev">
									<span class="glyphicon glyphicon-chevron-left"></span>
								</a>
							<#else>
								<span class="glyphicon glyphicon-chevron-left"></span>
							</#if>
							
						</li>
						<#if pagenum lte 3>
							<#list 1..pagenum as pn>
								<li <#if page.currentPage == pn>class="active"</#if>>
									<a href="javascript:queryImg(${pn});" class="cruda">${pn}</a>
								</li>
							</#list>
						</#if>
						<#if pagenum gt 3>
							<#if page.currentPage lt 2>
								<#list 1..pagenum as pn>
									<#if pn lt 3>
										<li <#if page.currentPage == pn>class="active"</#if>>
											<a href="javascript:queryImg(${pn});" class="cruda">${pn}</a>
										</li>
									</#if>
										
								</#list>
							</#if>
							<#if page.currentPage gte 2>
								<#if page.currentPage-1 gt 0>
									<li class="disabled">
										<a href="javascript:void(0);">...</a>
									</li>
									
								</#if>
								<#list 1..pagenum as pn>
									<#if (page.currentPage-1 <= pn)&&(pn <= page.currentPage+1)>
										<li <#if page.currentPage == pn>class="active"</#if>>
											<a href="javascript:queryImg(${pn});" class="cruda">${pn}</a>
										</li>
									</#if>
										
								</#list>
							</#if>
							<#if page.currentPage+1 lt pagenum>
								<li class="disabled">
									<a href="javascript:void(0);">...</a>
								</li>
							</#if>
						</#if>
						
						<li <#if page.currentPage == pagenum>class="disabled"</#if>>
							<#if page.currentPage == pagenum>
									<span class="glyphicon glyphicon-chevron-right"></span>
							<#else>
								<a href="javascript:queryImg(${page.currentPage+1});" class="crud crudnext">
									<span class="glyphicon glyphicon-chevron-right"></span>
								</a>
							</#if>
						</li>
						<li	<#if page.currentPage == pagenum>class="disabled"</#if>>
							
							<#if page.currentPage == pagenum>
								<span class="glyphicon glyphicon-step-forward"></span>
							<#else>
								<a href="javascript:queryImg(${page.totalPage});" class="crud crudlast" data="page:${pagenum}">
								<span class="glyphicon glyphicon-step-forward"></span>
							</a>
							</#if>
							
							
						</li>
					</#if>
				    <#--------分页标签结束------->
				    
				</ul>
			</div>
		</div>
	</div>
	</form>
	</body>
</html>


<!-- basic scripts -->
<script src="${base}/ace/assets/js/jquery-1.11.3.min.js"></script>
<script src="${base}/ace/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="${base}/ace/assets/js/tree.min.js"></script>
<script src="${base}/ace/assets/js/select2.min.js"></script>
<script src="${base}/ace/assets/js/holder.min.js"></script>

<!-- ace scripts -->
<script src="${base}/ace/assets/js/ace-elements.min.js"></script>
<script src="${base}/ace/assets/js/ace.min.js"></script>
<!-- inline scripts related to this page -->

<script>
	//图片单击选中
	$(function(){
		 
		$(".thumbnail").click(function(){
		
		  <#if multi==true> 
		  	 if($(this).hasClass("selected"))
			 {
			  	$(this).removeClass("selected");
			 }else
			 {
			  	$(this).addClass("selected");
			 }
		  <#else>
		  	$(".thumbnail").removeClass("selected");
		  	$(this).addClass("selected");
		  </#if>
		});
	});
	
	//查询
	function queryImg(page)
	{
		$("#currentPage").val(page);
		$("#queryForm").submit();
	}
	
	
	//确定选择图片
	function confirmImginfo()
	{
		var imgArr = [];
		
		$(".thumbnail").each(function(){
		    if($(this).hasClass("selected"))
		    {
		    	var img = {};
		    	img.id=$(this).attr("imgId");
		    	img.url=$(this).attr("imgUrl");
		    	imgArr.push(img);
		    }
		});
  
		
		//回调父窗口的方法
		window.opener.chooseImgCB_${cbId}(imgArr);
		window.close();
	}

</script>