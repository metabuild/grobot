package org.metabuild.grobot.tasks.groovy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.metabuild.grobot.tasks.groovy.GroovyTask;

import groovy.lang.Script;

/**
 * @author jburbrid
 * @since 9/27/2012
 */
public class GroovyTaskTest {

	@Test
	public void testTask() {
		Script script = mock(Script.class);
		when(script.run()).thenReturn("OK!");
		GroovyTask task = new GroovyTask(script);
		assertEquals("OK!", task.run());
		assertEquals(1, task.getTimesRun());
	}

}
