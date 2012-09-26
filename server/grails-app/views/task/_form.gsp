<%@ page import="server.Task" %>



<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="task.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="40" required="" value="${taskInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="task.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="code" cols="40" rows="5" maxlength="1000" required="" value="${taskInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'dependencies', 'error')} ">
	<label for="dependencies">
		<g:message code="task.dependencies.label" default="Dependencies" />
		
	</label>
	<g:select name="dependencies" from="${server.Task.list()}" multiple="multiple" optionKey="id" size="5" value="${taskInstance?.dependencies*.id}" class="many-to-many"/>
</div>

