/**
 * 
 */
package org.metabuild.grobot.common.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author jburbrid
 * @since 12/14/2012
 */
public class TaskTest {

	@Test
	public void testConstructor() {
		Task task = new Task("name", "scriptName", new ArrayList<TaskExecution>());
		assertNotNull(task);
		assertNotNull(task.getId());
		assertEquals("name", task.getName());
		assertEquals("scriptName", task.getScriptName());
		assertEquals(0, task.getTaskExecutions().size());
	}
}
