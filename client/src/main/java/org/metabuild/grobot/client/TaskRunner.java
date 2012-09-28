package org.metabuild.grobot.client;

import java.io.IOException;

import org.metabuild.grobot.core.Task;

import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;

public class GrobotTaskRunner {

	private static final String DEFAULT_TASKS_DIR = "/dev/projects/grobot/client/tasks";
	private final GrobotTaskFactory taskFactory;
	private final String tasksDir;
	private GroovyScriptEngine engine;
	
	public GrobotTaskRunner(String tasksDir) throws IOException {
		this(tasksDir, new GroovyScriptEngine(tasksDir)); 
	}
	
	protected GrobotTaskRunner(String tasksDir, GroovyScriptEngine engine) {
		this(tasksDir, engine, new GrobotTaskFactory(tasksDir, engine));
	}

	protected GrobotTaskRunner(String tasksDir, GroovyScriptEngine engine, GrobotTaskFactory taskFactory) {
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
	
	public GrobotTaskFactory getGrobotTaskFactory() {
		return taskFactory;
	}
	
	public static void main(String args[]) {
		try {
			GrobotTaskRunner runner = new GrobotTaskRunner(DEFAULT_TASKS_DIR);
			for (Task task : runner.getGrobotTaskFactory().getTasks()) {
				System.out.println("Task: " + task.toString());
				task.run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
