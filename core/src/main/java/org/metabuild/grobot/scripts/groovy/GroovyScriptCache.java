/**
 * 
 */
package org.metabuild.grobot.scripts.groovy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.metabuild.grobot.scripts.ScriptWrapper;

/**
 * @author jburbridge
 *
 */
public class GroovyScriptCache {
	
	private final GroovyScriptFactory groovyScriptFactory;
	private final Map<String, GroovyScript> registry;
	
	
	/**
	 * @param groovyScriptFactory
	 */
	public GroovyScriptCache(GroovyScriptFactory groovyScriptFactory) {
		this(groovyScriptFactory, new HashMap<String,GroovyScript>());
	}

	
	/**
	 * @param groovyScriptFactory
	 * @param registry
	 */
	protected GroovyScriptCache(GroovyScriptFactory groovyScriptFactory, Map<String, GroovyScript> registry) {
		this.groovyScriptFactory = groovyScriptFactory;
		this.registry = loadTasks(registry);
	}
	
	/**
	 * @param registry map to be populated
	 * @return the populated registry map
	 */
	protected Map<String, GroovyScript> loadTasks(Map<String, GroovyScript> registry) {
		for (ScriptWrapper task : groovyScriptFactory.getScripts()) {
			registry.put(task.getHash(), (GroovyScript) task);
		}
		return registry;
	}
	
	/**
	 * @param hash
	 * @return the task
	 */
	public GroovyScript get(String hash) {
		return registry.get(hash);
	}
	
	/**
	 * @return all the scripts currently cached
	 */
	public List<GroovyScript> getAll() {
		return new ArrayList<GroovyScript>(registry.values());
	}
}
