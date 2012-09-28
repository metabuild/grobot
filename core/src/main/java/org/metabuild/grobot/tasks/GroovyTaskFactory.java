package org.metabuild.grobot.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

/**
 * The TaskFactory is responsible for loading the groovy scripts from the tasks directory
 * and creating instances of grobot tasks that can be executed against a target node
 * 
 * @author jburbridge
 * @since 9/27/2012
 */
public class GroovyTaskFactory {
	
	private static final Logger LOGGER = Logger.getLogger(GroovyTaskFactory.class.getName());
	private static final String DOT_GROOVY = ".groovy";
	private static final GroovyBindingBuilder bindingBuilder = GroovyBindingBuilder.getInstance();
	
	private File tasksDir;
	private GroovyScriptEngine engine;
	private Binding binding;
	
	/**
	 * Default constructor which uses bindings populated with system variables
	 * 
	 * @param tasksDir
	 * @param engine
	 */
	public GroovyTaskFactory(String tasksDir, GroovyScriptEngine engine) {
		this(new File(tasksDir), engine, bindingBuilder.getBinding());
	}
	
	/**
	 * Constructor with DI for unit testing
	 * 
	 * @param tasksDir
	 * @param engine
	 * @param binding
	 */
	protected GroovyTaskFactory(File tasksDir, GroovyScriptEngine engine, Binding binding) {
		this.tasksDir = tasksDir;
		this.engine = engine;
		this.binding = binding;
		
		if (!tasksDir.isDirectory() || !tasksDir.canRead()) {
			throw new RuntimeException("Could not find the tasks directory " + tasksDir.getAbsolutePath());
		}
	}
	
	/**
	 * Recurses through the tasks directory and loads the groovy files
	 * 
	 * @return a list of Tasks
	 */
	public List<GroovyTask> getTasks() {
		List<GroovyTask> tasks = new ArrayList<GroovyTask>();
		for (File file : getFiles(tasksDir)) {
			try {
				Script script = engine.createScript(file.getAbsolutePath(), this.binding);
				tasks.add(new GroovyTask(script));
			} catch (ResourceException e) {
				LOGGER.log(Level.WARNING, "Could not load task from " + file.getAbsolutePath(), e);
			} catch (ScriptException e) {
				LOGGER.log(Level.WARNING, "Could not load task from " + file.getAbsolutePath(), e);
			}
		}
		return tasks;
	}
	
	/**
	 * Recursively gets all of the files in tasksDir that end in ".groovy"
	 * @param tasksDir
	 * @return a List of files
	 */
	protected List<File> getFiles(File tasksDir) {
		List<File> files = new ArrayList<File>();
		if (tasksDir.isDirectory()) {
			for (File file : tasksDir.listFiles()) {
				if (file.isDirectory()) {
					files.addAll(getFiles(file));
				} else if (file.getName().endsWith(DOT_GROOVY)) {
					files.add(file);
				}
			}
		}
		return files;
	}

	/**
	 * @return the tasksDir
	 */
	public File getTasksDir() {
		return tasksDir;
	}

	/**
	 * @return the engine
	 */
	public GroovyScriptEngine getEngine() {
		return engine;
	}

	/**
	 * @return the binding
	 */
	public Binding getBinding() {
		return binding;
	}
}
