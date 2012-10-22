/**
 * 
 */
package org.metabuild.grobot.tasks.groovy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.metabuild.grobot.tasks.Task;

/**
 * @author jburbridge
 *
 */
public class GroovyTaskCache {
	
	private final GroovyTaskFactory groovyTaskFactory;
	private final Map<String, GroovyTask> registry;
	
	
	/**
	 * @param groovyTaskFactory
	 */
	public GroovyTaskCache(GroovyTaskFactory groovyTaskFactory) {
		this(groovyTaskFactory, new HashMap<String,GroovyTask>());
	}

	
	/**
	 * @param groovyTaskFactory
	 * @param registry
	 */
	protected GroovyTaskCache(GroovyTaskFactory groovyTaskFactory, Map<String, GroovyTask> registry) {
		this.groovyTaskFactory = groovyTaskFactory;
		this.registry = loadTasks(registry);
	}
	
	/**
	 * @param registry map to be populated
	 * @return the populated registry map
	 */
	protected Map<String, GroovyTask> loadTasks(Map<String, GroovyTask> registry) {
		for (Task task : groovyTaskFactory.getTasks()) {
			registry.put(task.toString(), (GroovyTask) task);
		}
		return registry;
	}
	
	/**
	 * @param name
	 * @return the task
	 */
	public GroovyTask get(String name) {
		return registry.get(name);
	}
	
	/**
	 * @return all the tasks currently cached
	 */
	public List<GroovyTask> getAll() {
		return new ArrayList<GroovyTask>(registry.values());
	}
}
