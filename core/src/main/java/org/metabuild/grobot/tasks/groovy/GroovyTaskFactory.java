package org.metabuild.grobot.tasks.groovy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import org.metabuild.grobot.tasks.BindingProvider;
import org.metabuild.grobot.tasks.Task;
import org.metabuild.grobot.tasks.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The TaskFactory is responsible for loading the groovy scripts from the tasks directory
 * and creating instances of grobot tasks that can be executed against a target node
 * 
 * @author jburbridge
 * @since 9/27/2012
 */
public class GroovyTaskFactory implements TaskFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GroovyTaskFactory.class.getName());
	private static final String DOT_GROOVY = ".groovy";
	
	private final BindingProvider bindingProvider;
	private final File tasksDir;
	private final GroovyScriptEngine engine;
	
	/**
	 * @param bindingProvider
	 * @param tasksDir
	 * @param engine
	 */
	public GroovyTaskFactory(BindingProvider bindingProvider, File tasksDir, GroovyScriptEngine engine) {
		this.bindingProvider = bindingProvider;
		this.tasksDir = tasksDir;
		this.engine = engine;
		if (!tasksDir.isDirectory() || !tasksDir.canRead()) {
			throw new RuntimeException("Could not find the tasks directory " + tasksDir.getAbsolutePath());
		}
	}

	/**
	 * Recurses through the tasks directory and loads the groovy files
	 * 
	 * @return a list of Tasks
	 */
	@Override
	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		for (File file : getFiles(tasksDir)) {
			try {
				Script script = engine.createScript(file.getAbsolutePath(), getBinding());
				tasks.add(new GroovyTask(script));
			} catch (ResourceException e) {
				LOGGER.warn("Could not load task from {}", file.getAbsolutePath(), e);
			} catch (ScriptException e) {
				LOGGER.warn("Could not load task from {}", file.getAbsolutePath(), e);
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
	 * @return the binding provider
	 */
	public BindingProvider getBindingProvider() {
		return bindingProvider;
	}

	/**
	 * @return the binding
	 */
	public Binding getBinding() {
		return bindingProvider.getBinding();
	}
}
