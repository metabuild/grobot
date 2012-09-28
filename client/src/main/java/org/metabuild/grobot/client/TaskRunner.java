package org.metabuild.grobot.client;

import java.io.IOException;

import org.metabuild.grobot.client.TaskRunner;
import org.metabuild.grobot.core.Task;
import org.metabuild.grobot.core.TaskFactory;

import groovy.util.GroovyScriptEngine;

public class TaskRunner {

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
		try {
			TaskRunner runner = new TaskRunner("/dev/projects/grobot/client/tasks");
			for (Task task : runner.getGrobotTaskFactory().getTasks()) {
				System.out.println("Task: " + task.toString());
				task.run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
