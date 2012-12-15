package org.metabuild.grobot.scripts.groovy;

import groovy.lang.Binding;

import java.util.Map;
import java.util.Map.Entry;

import org.metabuild.grobot.scripts.BindingProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds a Groovy Binding populated with system properties which will be passed along
 * to the GroovyScriptEnging.
 * 
 * @author jburbrid
 * @since 9/27/2012
 */
public class GroovyBindingProvider implements BindingProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(GroovyBindingProvider.class.getName());

	private Map<Object,Object> bindingParameters;
	private Binding binding;
	
	/**
	 * @param bindingParameters
	 * @param binding
	 */
	public GroovyBindingProvider(Map<Object, Object> bindingParameters,
			Binding binding) {
		this.bindingParameters = bindingParameters;
		this.binding = binding;
	}
	
	/**
	 * @return the bindingParameters
	 */
	public Map<Object, Object> getBindingParameters() {
		return bindingParameters;
	}
	
	/**
	 * @param bindingParameters the bindingParameters to set
	 */
	public void setBindingParameters(Map<Object, Object> bindingParameters) {
		this.bindingParameters = bindingParameters;
	}
	
	/**
	 * @return the binding
	 */
	@Override
	public Binding getBinding() {
		LOGGER.debug("Populating binding with parameter map");
		for (Entry<Object,Object> entry : bindingParameters.entrySet()) {
			if (entry.getKey() != null)
				binding.setProperty(entry.getKey().toString(), entry.getValue());
		}
		return binding;
	}
	/**
	 * @param binding the binding to set
	 */
	public void setBinding(Binding binding) {
		this.binding = binding;
	}

	
}

