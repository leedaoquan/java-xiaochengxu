<#if specMap??>
	
	<#list specMap?keys as specName>
		<b>${specName}</b>:
		<select name="specId">
			<#list specMap[specName] as spec>
	    		<option value="${spec.id}">${spec.value}</option>
	    	</#list>
		</select>
		&nbsp;&nbsp;&nbsp;&nbsp;
	</#list>
<#else>
	<font color="red">请为该商品添加规格信息后，再来新增sku。</font>
</#if>