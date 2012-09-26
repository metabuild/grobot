<%@ page import="server.TargetNode" %>



<div class="fieldcontain ${hasErrors(bean: nodeInstance, field: 'hostname', 'error')} required">
	<label for="hostname">
		<g:message code="node.hostname.label" default="Hostname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="hostname" maxlength="80" required="" value="${nodeInstance?.hostname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: nodeInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="node.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${nodeInstance?.active}" />
</div>

