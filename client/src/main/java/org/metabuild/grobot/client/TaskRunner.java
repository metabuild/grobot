package org.metabuild.grobot.client;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.metabuild.grobot.client.TaskRunner;
import org.metabuild.grobot.tasks.BindingProvider;
import org.metabuild.grobot.tasks.Task;
import org.metabuild.grobot.tasks.groovy.GroovyBindingProvider;
import org.metabuild.grobot.tasks.groovy.GroovyTaskFactory;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

/**
 * Example task runner
 * 
 * @author jburbrid
 * @since 9/27/2012
 */
public class TaskRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskRunner.class);
	private static final String DEFAULT_TASKS_DIR = System.getProperty("user.dir") + "/tasks";
	private final GroovyTaskFactory taskFactory;
	private final String tasksDir;
	private GroovyScriptEngine engine;
	
	/**
	 * Given a directory containing tasks, instantiate a TaskRunner
	 * 
	 * @param tasksDir
	 * @throws IOException
	 */
	public TaskRunner(String tasksDir) throws IOException {
		this(tasksDir, new GroovyScriptEngine(tasksDir)); 
	}
	
	protected TaskRunner(String tasksDir, GroovyScriptEngine engine) {
		this(tasksDir, engine, new GroovyTaskFactory(getGroovyBindingProvider(), new File(tasksDir), engine));
	}

	protected TaskRunner(String tasksDir, GroovyScriptEngine engine, GroovyTaskFactory taskFactory) {
		this.tasksDir = tasksDir;
		this.taskFactory = taskFactory;
		this.engine = engine;
	}
	
	public String getTasksDir() {
		return tasksDir;
	}
	
	public GroovyScriptEngine getGroovyScriptEngine() {
		return engine;
	}
	
	public GroovyTaskFactory getGroovyTaskFactory() {
		return taskFactory;
	}
	
	protected static BindingProvider getGroovyBindingProvider() {
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		return new GroovyBindingProvider(paramMap, new Binding());
	}

	public void runTasks() {
		for (Task task : getGroovyTaskFactory().getTasks()) {
			LOGGER.info("<<< Loaded task: {} >>>", task);
			try {
				LOGGER.info("<<< Running task: {} >>>", task);
				Object result = task.run();
				LOGGER.info("<<< Task \"{}\" returned: {} >>>", task, result);
				LOGGER.info("<<< Task \"{}\" has run {} times >>>", task, task.getTimesRun());
			} catch (Exception e) {
				LOGGER.error("Task \"{}\" threw an exception: {}", task, e.getMessage());
			}
		}
	}
	
	public static void main(String args[]) {
		try {
			new TaskRunner(DEFAULT_TASKS_DIR).runTasks();
		} catch (IOException e) {
			LOGGER.warn("The TaskRunner threw an exception {}", e.getClass());
		}
	}
}
