package org.metabuild.grobot.core;

import groovy.lang.Binding;

import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Builds a Groovy Binding populated with system properties which will be passed along
 * to the GroovyScriptEnging.
 * 
 * @author jburbrid
 * @since 9/27/2012
 */
public class GroovyBindingBuilder {

	private static final Logger LOGGER = Logger.getLogger(GroovyBindingBuilder.class.getName());
	private static final GroovyBindingBuilder INSTANCE = new GroovyBindingBuilder();
	private Binding binding;

	private GroovyBindingBuilder() {
		this.binding = new Binding();
	}
	
	public static GroovyBindingBuilder getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Iterates over the system properties and populates them with bindings
	 * 
	 * @return a populated binding instance 
	 */
	public Binding getBinding() {
		for (Entry<Object,Object> property : System.getProperties().entrySet()) {
			LOGGER.log(Level.FINE, "property: " + property.toString());
			binding.setVariable(property.getKey().toString(), property.getValue().toString());
		}
		return binding;
	}
}

