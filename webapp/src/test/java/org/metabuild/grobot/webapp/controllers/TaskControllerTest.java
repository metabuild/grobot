package org.metabuild.grobot.webapp.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.metabuild.grobot.scripts.groovy.GroovyScript;
import org.metabuild.grobot.scripts.groovy.GroovyScriptCache;
import org.metabuild.grobot.webapp.AbstractSpringEnabledTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;


/**
 * @author jburbridge
 * @since 10/23/2012
 */
public class TaskControllerTest extends AbstractSpringEnabledTest {

	private static final List<GroovyScript> tasks = new ArrayList<GroovyScript>();
	
	@Before
	public void initTaskController() {
		GroovyScript task = mock(GroovyScript.class);
		when(task.toString()).thenReturn("MyMockTask");
		tasks.add(task);
	}
	
	@Test
	public void testList() {
		
		GroovyScriptCache taskCache = mock(GroovyScriptCache.class);
		when(taskCache.getAll()).thenReturn(tasks);
		
		TaskController controller = new TaskController();
		
		ReflectionTestUtils.setField(controller, "taskCache", taskCache);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.list(uiModel);
		
		assertNotNull(result);
		assertEquals("scripts/list", result);
		
		@SuppressWarnings("unchecked")
		List<GroovyScript> modelTasks = (List<GroovyScript>) uiModel.get("scripts");
		
		assertEquals(1, modelTasks.size());
	}

}
