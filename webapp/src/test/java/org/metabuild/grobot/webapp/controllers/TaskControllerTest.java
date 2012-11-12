package org.metabuild.grobot.webapp.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.metabuild.grobot.tasks.groovy.GroovyTask;
import org.metabuild.grobot.tasks.groovy.GroovyTaskCache;
import org.metabuild.grobot.webapp.AbstractSpringEnabledTest;

import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;


/**
 * @author jburbridge
 * @since 10/23/2012
 */
public class TaskControllerTest extends AbstractSpringEnabledTest {

	private static final List<GroovyTask> tasks = new ArrayList<GroovyTask>();
	
	@Before
	public void initTaskController() {
		GroovyTask task = mock(GroovyTask.class);
		when(task.toString()).thenReturn("MyMockTask");
		tasks.add(task);
	}
	
	@Test
	public void testList() {
		
		GroovyTaskCache taskCache = mock(GroovyTaskCache.class);
		when(taskCache.getAll()).thenReturn(tasks);
		
		TaskController controller = new TaskController();
		
		ReflectionTestUtils.setField(controller, "taskCache", taskCache);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.list(uiModel);
		
		assertNotNull(result);
		assertEquals("tasks/list", result);
		
		@SuppressWarnings("unchecked")
		List<GroovyTask> modelTasks = (List<GroovyTask>) uiModel.get("tasks");
		
		assertEquals(1, modelTasks.size());
	}

}
