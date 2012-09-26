<%@ page import="server.TargetGroup" %>



<div class="fieldcontain ${hasErrors(bean: nodeGroupInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="nodeGroup.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="40" required="" value="${nodeGroupInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: nodeGroupInstance, field: 'parent', 'error')} ">
	<label for="parent">
		<g:message code="nodeGroup.parent.label" default="Parent" />
		
	</label>
	<g:select id="parent" name="parent.id" from="${server.NodeGroup.list()}" optionKey="id" value="${nodeGroupInstance?.parent?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: nodeGroupInstance, field: 'nodes', 'error')} ">
	<label for="nodes">
		<g:message code="nodeGroup.nodes.label" default="Nodes" />
		
	</label>
	<g:select name="nodes" from="${server.Node.list()}" multiple="multiple" optionKey="id" size="5" value="${nodeGroupInstance?.nodes*.id}" class="many-to-many"/>
</div>

