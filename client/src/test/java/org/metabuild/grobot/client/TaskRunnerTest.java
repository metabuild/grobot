package org.metabuild.grobot.client;

import groovy.util.GroovyScriptEngine;

import java.io.IOException;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.metabuild.grobot.client.TaskRunner;

/**
 * @author jburbridge
 *
 */
public class TaskRunnerTest {

	/**
	 * Test method for {@link org.metabuild.grobot.client.TaskRunner#GrobotTaskRunner(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	public void testGrobotTaskRunnerOK() throws IOException {
		TaskRunner runner = new TaskRunner("tasks");
		assertNotNull(runner);
		assertNotNull(runner.getTasksDir());
		assertNotNull(runner.getGroovyTaskFactory());
		assertNotNull(runner.getGroovyScriptEngine());
	}

	/**
	 * Test method for {@link org.metabuild.grobot.client.TaskRunner#GrobotTaskRunner(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test(expected=NullPointerException.class)
	public void testGrobotTaskRunnerFail() throws IOException {
		new TaskRunner(null);
	}

	/**
	 * Test method for {@link org.metabuild.grobot.client.TaskRunner#GrobotTaskRunner(java.lang.String, groovy.util.GroovyScriptEngine)}.
	 * @throws IOException 
	 */
	@Test
	public void testGrobotTaskRunnerStringGroovyScriptEngineOK() throws IOException {
		TaskRunner runner = new TaskRunner("tasks", new GroovyScriptEngine("tasks"));
		assertNotNull(runner);
		assertNotNull(runner.getTasksDir());
		assertNotNull(runner.getGroovyTaskFactory());
		assertNotNull(runner.getGroovyScriptEngine());
	}
	
	@Test
	public void testRunTasks() {
		try {
			new TaskRunner("tasks").runTasks();
		} catch (IOException e) {
			fail("Should have run successfully but threw an exception: " + e.getMessage());
		}
	}
}
