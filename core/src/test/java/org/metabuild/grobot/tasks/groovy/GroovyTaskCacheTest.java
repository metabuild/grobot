/**
 * 
 */
package org.metabuild.grobot.tasks.groovy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.metabuild.grobot.tasks.Task;

/**
 * @author jburbridge
 * @since 10/23/2012
 */
public class GroovyTaskCacheTest {

	/**
	 * Test method for {@link org.metabuild.grobot.tasks.groovy.GroovyTaskCache#GroovyTaskCache(org.metabuild.grobot.tasks.groovy.GroovyTaskFactory, java.util.Map)}.
	 */
	@Test
	public void testGroovyTaskCacheGroovyTaskFactoryMapOfStringGroovyTask() {
		assertNotNull(new GroovyTaskCache(getMockTaskFactory(), new HashMap<String, GroovyTask>()));
	}

	/**
	 * Test method for {@link org.metabuild.grobot.tasks.groovy.GroovyTaskCache#get(java.lang.String)}.
	 */
	@Test
	public void testGetTaskWithMatch() {
		GroovyTaskCache taskCache = new GroovyTaskCache(getMockTaskFactory(), new HashMap<String, GroovyTask>());
		assertNotNull(taskCache.get("MyMockTaskHash"));
		assertEquals("MyMockTaskName",taskCache.get("MyMockTaskHash").toString());
		assertNull(taskCache.get(null));
	}

	public static GroovyTaskFactory getMockTaskFactory() {
		
		GroovyTaskFactory taskFactory = mock(GroovyTaskFactory.class);
		GroovyTask task = mock(GroovyTask.class);
		when(task.getHash()).thenReturn("MyMockTaskHash");
		when(task.toString()).thenReturn("MyMockTaskName");
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task);
		when(taskFactory.getTasks()).thenReturn(tasks);
		return taskFactory;
	}
}
