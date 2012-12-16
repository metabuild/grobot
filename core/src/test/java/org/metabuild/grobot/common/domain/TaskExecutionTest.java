/**
 * 
 */
package org.metabuild.grobot.common.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

/**
 * @author jburbrid
 * @since 12/14/2012
 */
public class TaskExecutionTest {

	@Test
	public void testConstructor() {
		Task parentTask = new Task("name", "scriptName", new ArrayList<TaskExecution>());
		TaskExecution taskExecution = new TaskExecution(parentTask, new Date(0), new Date(1), new Date(2));
		assertNotNull(taskExecution);
		assertNotNull(taskExecution.getId());
		assertEquals(0, taskExecution.getScheduleStartTime().getTime());
		assertEquals(1, taskExecution.getActualStartTime().getTime());
		assertEquals(2, taskExecution.getEndTime().getTime());
	}
}
