package org.metabuild.grobot.core;

import groovy.lang.Binding;

import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Builds a binding populated with system properties
 * 
 * @author jburbrid
 * @since 9/27/2012
 */
public class BindingBuilder {

	private static final Logger LOGGER = Logger.getLogger(BindingBuilder.class.getName());
	private static final BindingBuilder INSTANCE = new BindingBuilder();
	private Binding binding;

	private BindingBuilder() {
		this.binding = new Binding();
	}
	
	public static BindingBuilder getInstance() {
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

