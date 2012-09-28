package org.metabuild.grobot.core;

import groovy.util.GroovyScriptEngine;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.metabuild.grobot.core.TaskFactory;

/**
 * @author jburbridge
 *
 */
public class TaskFactoryTest {
	
	private final static String TASKS_DIR = "src/test/resources/tasks";

	@Test
	public void testGrobotTaskFactory() throws IOException {
		TaskFactory factory = new TaskFactory(TASKS_DIR, new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory);
	}

	@Test
	public void testGetTasks() throws IOException {
		TaskFactory factory = new TaskFactory(TASKS_DIR, new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory.getTasks());
	}

	@Test
	public void testGetFiles() throws IOException {
		TaskFactory factory = new TaskFactory(TASKS_DIR, new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory.getFiles(new File(TASKS_DIR)));
	}

	@Test
	public void testGetBinding() throws IOException {
		TaskFactory factory = new TaskFactory(TASKS_DIR, new GroovyScriptEngine(TASKS_DIR));
		assertNotNull(factory.getBinding());
	}

}
