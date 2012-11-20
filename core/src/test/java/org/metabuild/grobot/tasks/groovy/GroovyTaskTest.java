package org.metabuild.grobot.tasks.groovy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import groovy.lang.Script;

import org.junit.Test;
import org.metabuild.grobot.tasks.Task;

/**
 * @author jburbrid
 * @since 9/27/2012
 */
public class GroovyTaskTest {

	@Test
	public void testTask() {
		Script script = mock(Script.class);
		when(script.run()).thenReturn("OK!");
		Task task = new GroovyTask(script);
		assertEquals(0, task.getTimesRun());
		assertFalse(task.hasRun());
		assertEquals("OK!", task.run());
		assertEquals(1, task.getTimesRun());
		assertTrue(task.hasRun());
	}
}
