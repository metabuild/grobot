package org.metabuild.grobot.tasks.groovy;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.metabuild.grobot.tasks.BindingProvider;
import org.metabuild.grobot.tasks.Task;
import org.metabuild.grobot.tasks.groovy.GroovyBindingProvider;
import org.metabuild.grobot.tasks.groovy.GroovyTaskFactory;

/**
 * @author jburbridge
 *
 */
public class GroovyTaskFactoryTest {
	
	private final static String TASKS_DIR = "src/test/resources/tasks";

	@Test
	public void testGrobotTaskFactoryValidDirectory() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(getGroovyBindingProvider(), new File(TASKS_DIR), new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory);
	}

	@Test(expected=RuntimeException.class)
	public void testGrobotTaskFactoryInvalidDirectory() throws IOException {
		new GroovyTaskFactory( getGroovyBindingProvider(), new File(TASKS_DIR + "/b0rk3n"), new GroovyScriptEngine(TASKS_DIR + "/b0rk3n"));
		fail("Should have thrown a RuntimeException but didn't");
	}

	@Test
	public void testGetTasks() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(getGroovyBindingProvider(), new File(TASKS_DIR), new GroovyScriptEngine(TASKS_DIR));
		List<Task> tasks = factory.getTasks();
		assertNotNull(tasks);
		assertTrue(tasks.size() > 0);
	}

	@Test
	public void testGetFiles() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(getGroovyBindingProvider(), new File(TASKS_DIR),  new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory.getFiles(new File(TASKS_DIR)));
	}

	@Test
	public void testGetBinding() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(getGroovyBindingProvider(), new File(TASKS_DIR), new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory.getBinding());
	}

	@Test
	public void testGetEngine() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(getGroovyBindingProvider(), new File(TASKS_DIR), new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory.getEngine());
	}

	@Test
	public void testGetTasksDir() throws IOException {
		GroovyTaskFactory factory = new GroovyTaskFactory(getGroovyBindingProvider(), new File(TASKS_DIR), new GroovyScriptEngine(TASKS_DIR));
		assertEquals(TASKS_DIR, factory.getTasksDir().getPath());
	}

	protected BindingProvider getGroovyBindingProvider() {
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		return new GroovyBindingProvider(paramMap, new Binding());
	}
}
