package org.metabuild.grobot.client;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.metabuild.grobot.client.TaskRunner;
import org.metabuild.grobot.core.Task;
import org.metabuild.grobot.core.TaskFactory;

import groovy.util.GroovyScriptEngine;

/**
 * Example task runner
 * 
 * @author jburbrid
 * @since 9/27/2012
 */
public class TaskRunner {

	private static final Logger LOGGER = Logger.getLogger(TaskRunner.class);
	private static final String DEFAULT_TASKS_DIR = System.getProperty("user.dir") + "/tasks";
	private final TaskFactory taskFactory;
	private final String tasksDir;
	private GroovyScriptEngine engine;
	
	public TaskRunner(String tasksDir) throws IOException {
		this(tasksDir, new GroovyScriptEngine(tasksDir)); 
	}
	
	protected TaskRunner(String tasksDir, GroovyScriptEngine engine) {
		this(tasksDir, engine, new TaskFactory(tasksDir, engine));
	}

	protected TaskRunner(String tasksDir, GroovyScriptEngine engine, TaskFactory taskFactory) {
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
	
	public TaskFactory getGrobotTaskFactory() {
		return taskFactory;
	}
	
	public static void main(String args[]) {
		TaskRunner runner;
		try {
			runner = new TaskRunner(DEFAULT_TASKS_DIR);
			for (Task task : runner.getGrobotTaskFactory().getTasks()) {
				LOGGER.warn("Running task: " + task.toString());
				try {
					Object result = task.run();
					System.out.println("Task returned: " + result);
				} catch (Exception e) {
					LOGGER.warn("Could not run task " + task.toString(), e);
				}
				System.out.println("\n");
			}
		} catch (IOException e) {
			LOGGER.warn("Could not run tasks", e);
		}
	}
}
