/**
 * 
 */
package org.metabuild.grobot.tasks.groovy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.metabuild.grobot.tasks.Task;

/**
 * @author jburbridge
 *
 */
public class GroovyTaskCacheTest {

	/**
	 * Test method for {@link org.metabuild.grobot.tasks.groovy.GroovyTaskCache#GroovyTaskCache(org.metabuild.grobot.tasks.groovy.GroovyTaskFactory, java.util.Map)}.
	 */
	@Test
	public void testGroovyTaskCacheGroovyTaskFactoryMapOfStringGroovyTask() {
		GroovyTaskCache taskCache = new GroovyTaskCache(getMockTaskFactory(), new HashMap<String, GroovyTask>());
		assertNotNull(taskCache);
		assertTrue(taskCache instanceof GroovyTaskCache);
	}

	/**
	 * Test method for {@link org.metabuild.grobot.tasks.groovy.GroovyTaskCache#getTask(java.lang.String)}.
	 */
	@Test
	public void testGetTaskWithMatch() {
		GroovyTaskCache taskCache = new GroovyTaskCache(getMockTaskFactory(), new HashMap<String, GroovyTask>());
		assertNotNull(taskCache.getTask("MyMockTask"));
		assertEquals("MyMockTask",taskCache.getTask("MyMockTask").toString());
		assertNull(taskCache.getTask(null));
	}

	private GroovyTaskFactory getMockTaskFactory() {
		
		GroovyTaskFactory taskFactory = mock(GroovyTaskFactory.class);
		GroovyTask task = mock(GroovyTask.class);
		when(task.toString()).thenReturn("MyMockTask");
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task);
		when(taskFactory.getTasks()).thenReturn(tasks);
		return taskFactory;
	}
}
