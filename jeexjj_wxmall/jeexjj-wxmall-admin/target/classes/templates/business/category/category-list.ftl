<#--
	<th>商家</th>
	<th>关键词</th>
	 <th>父类目id</th>
-->
<#include "/templates/xjj-list.ftl">
<table class='table table-responsive'>
	<thead>
		
        <th>名称</th>
        <th>简介</th>
        <th>类目图标</th>
        <th>类目图片</th>
        <th>级别</th>
        <th>创建时间</th>
        <th>状态</th>
        <th>操作</th>
	</thead>
	<tbody>
<#list categoryList as c>
	<tr style="background:#c4e1e1">
		<td>
			<#if c.subCategoryList?? && c.subCategoryList?size gt 0><a href="javascript:void(0)" onclick="javascript:toggle(${c.id},this)" id="a${c.id}"><span class="glyphicon glyphicon-plus"></span></a></#if>
			<b>${c.name}</b>
		</td>
		<td>${c.frontDesc}</td>
		
		<td>
		    <img src="${c.iconUrl}" width="30"/>
		</td>
		<td>
		    <img src="${c.imgUrl}" width="40"/>
		</td>
			
		<td>
		    ${c.level}
		</td>
		
		<td>
		    ${c.addTime?string('yyyy-MM-dd')}
		</td>
		<td>
            <span class="label <#if c.status=XJJConstants.COMMON_STATUS_VALID>label-info</#if> arrowed-in arrowed-in-right">${XJJDict.getText(c.status)}</span>
		</td>
		<td>
        	
        	<div class="hidden-sm hidden-xs btn-group">
				<@button type="info" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/category/input/${c.id}','修改类目','${tabId}');" bigger="120"></@button>
				<@button type="danger" icon="fa fa-trash-o" onclick="XJJ.del('${base}/business/category/delete/${c.id}','删除类目？',false,{id:'${tabId}'});" bigger="120"></@button>
				
				
				<#if c.status=XJJConstants.COMMON_STATUS_VALID>
					<@button type="warning" icon="fa fa-ban" onclick="XJJ.del('${base}/business/category/disable/${c.id}','禁用类目？',false,{id:'${tabId}'});" bigger="120"></@button>
				<#else>
					<@button type="success" icon="fa fa-check" onclick="XJJ.del('${base}/business/category/enable/${c.id}','启用类目？',false,{id:'${tabId}'});" bigger="120"></@button>
				</#if>
				<@button type="success" icon="glyphicon glyphicon-plus" onclick="XJJ.add('${base}/business/category/${c.id}/input','添加子类目','${tabId}');" bigger="118"></@button>
			</div>
        
        </td>
	</tr>
	<#if c.subCategoryList?? && c.subCategoryList?size gt 0>
		<#list c.subCategoryList as cc>
		<tr class="${c.id}" style="display:none;">
			<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<#if cc.subCategoryList?? && cc.subCategoryList?size gt 0><a href="javascript:void(0)" onclick="javascript:toggle(${cc.id},this)" id="a${cc.id}"><span class="glyphicon glyphicon-plus"></span></a></#if>${cc.name}</td>
			<td>${cc.frontDesc}</td>
			<td>
			    <img src="${cc.iconUrl}" width="30"/>
			</td>
			<td>
			    <img src="${cc.imgUrl}" width="40"/>
			</td>
				
			<td>
			    ${cc.level}
			</td>
			
			<td>
			    ${cc.addTime?string('yyyy-MM-dd')}
			</td>
			<td>
                <span class="label <#if cc.status=XJJConstants.COMMON_STATUS_VALID>label-info</#if> arrowed-in arrowed-in-right">${XJJDict.getText(cc.status)}</span>
			</td>
			<td>
	       
	       		<div class="hidden-sm hidden-xs btn-group">
					<@button type="info" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/category/input/${cc.id}','修改类目','${tabId}');" bigger="120"></@button>
					<@button type="danger" icon="fa fa-trash-o" onclick="XJJ.del('${base}/business/category/delete/${cc.id}','删除类目？',false,{id:'${tabId}'});" bigger="120"></@button>
					
					
					<#if cc.status=XJJConstants.COMMON_STATUS_VALID>
						<@button type="warning" icon="fa fa-ban" onclick="XJJ.del('${base}/business/category/disable/${cc.id}','禁用类目？',false,{id:'${tabId}'});" bigger="120"></@button>
					<#else>
						<@button type="success" icon="fa fa-check" onclick="XJJ.del('${base}/business/category/enable/${cc.id}','启用类目？',false,{id:'${tabId}'});" bigger="120"></@button>
					</#if>
				</div>
	       
	        </td>
		</tr>
		</#list>
	</#if>
</#list>
	</tbody>
</table>