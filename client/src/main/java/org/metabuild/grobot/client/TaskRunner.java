package org.metabuild.grobot.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.metabuild.grobot.client.TaskRunner;
import org.metabuild.grobot.tasks.GroovyTask;
import org.metabuild.grobot.tasks.GroovyTaskFactory;

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
	
	public TaskRunner(String tasksDir) throws IOException {
		this(tasksDir, new GroovyScriptEngine(tasksDir)); 
	}
	
	protected TaskRunner(String tasksDir, GroovyScriptEngine engine) {
		this(tasksDir, engine, new GroovyTaskFactory(tasksDir, engine));
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
	
	public static void main(String args[]) {
		TaskRunner runner;
		try {
			runner = new TaskRunner(DEFAULT_TASKS_DIR);
			for (GroovyTask task : runner.getGroovyTaskFactory().getTasks()) {
				LOGGER.info("<<< Loaded task: {} >>>", task.toString());
				try {
					LOGGER.info("<<< Running task: {} >>>", task.toString());
					Object result = task.run();
					LOGGER.info("<<< Task {} returned: {} >>>", task.toString(), result);
					LOGGER.info("<<< Task {} has run {} times >>>", task.toString(), task.getTimesRun());
				} catch (Exception e) {
					LOGGER.error("{} threw an exception: {}", task.toString(), e.getMessage());
				}
			}
		} catch (IOException e) {
			LOGGER.warn("The TaskRunner threw an exception {}", e.getClass());
		}
	}
}
