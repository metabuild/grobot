package org.metabuild.grobot.core;

import groovy.util.GroovyScriptEngine;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.metabuild.grobot.core.GroovyTaskFactory;

/**
 * @author jburbridge
 *
 */
public class GroovyTaskFactoryTest {
	
	private final static String TASKS_DIR = "src/test/resources/tasks";

	@Test
	public void testGrobotTaskFactoryValidDirectory() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(TASKS_DIR, new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory);
	}

	@Test(expected=RuntimeException.class)
	public void testGrobotTaskFactoryInvalidDirectory() throws IOException {
		new GroovyTaskFactory(TASKS_DIR + "/b0rk3n", new GroovyScriptEngine(TASKS_DIR + "/b0rk3n"));
		fail("Should have thrown a RuntimeException but didn't");
	}

	@Test
	public void testGetTasks() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(TASKS_DIR, new GroovyScriptEngine(TASKS_DIR));
		List<GroovyTask> tasks = factory.getTasks();
		assertNotNull(tasks);
		assertTrue(tasks.size() > 0);
	}

	@Test
	public void testGetFiles() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(TASKS_DIR, new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory.getFiles(new File(TASKS_DIR)));
	}

	@Test
	public void testGetBinding() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(TASKS_DIR, new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory.getBinding());
	}

	@Test
	public void testGetEngine() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(TASKS_DIR, new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory.getEngine());
	}

	@Test
	public void testGetTasksDir() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(TASKS_DIR, new GroovyScriptEngine(TASKS_DIR));
		assertEquals(TASKS_DIR, factory.getTasksDir().getPath());
	}

}
