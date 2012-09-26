<%@ page import="server.Plan" %>



<div class="fieldcontain ${hasErrors(bean: planInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="plan.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="40" required="" value="${planInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: planInstance, field: 'parent', 'error')} required">
	<label for="parent">
		<g:message code="plan.parent.label" default="Parent" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="parent" name="parent.id" from="${server.Plan.list()}" optionKey="id" required="" value="${planInstance?.parent?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: planInstance, field: 'tasks', 'error')} ">
	<label for="tasks">
		<g:message code="plan.tasks.label" default="Tasks" />
		
	</label>
	<g:select name="tasks" from="${server.Task.list()}" multiple="multiple" optionKey="id" size="5" value="${planInstance?.tasks*.id}" class="many-to-many"/>
</div>

