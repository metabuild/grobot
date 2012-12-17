package org.metabuild.grobot.webapp.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.metabuild.grobot.common.domain.Task;
import org.metabuild.grobot.scripts.groovy.GroovyScript;
import org.metabuild.grobot.scripts.groovy.GroovyScriptCache;
import org.metabuild.grobot.server.service.TaskService;
import org.metabuild.grobot.webapp.AbstractSpringEnabledTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;


/**
 * @author jburbridge
 * @since 10/23/2012
 */
public class TaskControllerTest extends AbstractSpringEnabledTest {

	private static final List<Task> tasks = new ArrayList<Task>();
	
	@Before
	public void initTaskControllerTest() {
		Task task = mock(Task.class);
		when(task.getId()).thenReturn("883075bc-a392-415e-8957-131b70be821d");
		when(task.getName()).thenReturn("MyMockTask");
		tasks.add(task);
	}
	
	@Test
	public void testList() {
		
		TaskService taskService = mock(TaskService.class);
		when(taskService.findAll()).thenReturn(tasks);
		
		TaskController controller = new TaskController();
		
		ReflectionTestUtils.setField(controller, "taskService", taskService);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.list(uiModel);
		
		assertNotNull(result);
		assertEquals("tasks/list", result);
		
		@SuppressWarnings("unchecked")
		List<Task> modelTasks = (List<Task>) uiModel.get("tasks");
		
		assertEquals(1, modelTasks.size());
	}
}
