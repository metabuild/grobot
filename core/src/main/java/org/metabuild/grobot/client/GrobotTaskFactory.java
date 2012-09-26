package org.metabuild.grobot.client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.metabuild.grobot.core.Task;

import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

public class GrobotTaskFactory {
	
	private static Logger LOGGER = Logger.getLogger(GrobotTaskFactory.class.getName());
	
	private File tasksDir;
	private GroovyScriptEngine engine;
	
	protected GrobotTaskFactory(String tasksDir, GroovyScriptEngine engine) {
		this.tasksDir = new File(tasksDir);
		this.engine = engine;
	}
	
	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		Binding binding = getBinding();
		for (File file : getFiles(tasksDir)) {
			try {
				Script script = engine.createScript(file.getPath(), binding);
				tasks.add(new Task(script));
			} catch (ResourceException | ScriptException e) {
				e.printStackTrace();
			}
		}
		return tasks;
	}
	
	/**
	 * Recursively gets all of the files in tasksDir 
	 * @param tasksDir
	 * @return a List of files
	 */
	protected List<File> getFiles(File tasksDir) {
		List<File> files = new ArrayList<File>();
		if (tasksDir.isDirectory()) {
			for (File file : tasksDir.listFiles()) {
				if (file.isDirectory()) {
					files.addAll(getFiles(file));
				} else if (file.getName().endsWith(".groovy")) {
					files.add(file);
				}
			}
		}
		return files;
	}
	
	protected Binding getBinding() {
		Binding binding = new Binding();
		for (Entry<Object,Object> property : System.getProperties().entrySet()) {
			LOGGER.log(Level.FINE, "property: " + property.toString());
			binding.setVariable(property.getKey().toString(), property.getValue().toString());
		}
		return binding;
	}
}
