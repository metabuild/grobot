package org.metabuild.grobot.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import groovy.lang.Script;

/**
 * @author jburbrid
 * @since 9/27/2012
 */
public class TaskTest {

	@Test
	public void testTask() {
		Script script = mock(Script.class);
		when(script.run()).thenReturn("OK!");
		Task task = new Task(script);
		assertEquals("OK!", task.run());
		assertEquals(1, task.getTimesRun());
	}

}
