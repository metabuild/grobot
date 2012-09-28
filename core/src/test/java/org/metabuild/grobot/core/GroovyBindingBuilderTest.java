/**
 * 
 */
package org.metabuild.grobot.core;

import static org.junit.Assert.*;
import groovy.lang.Binding;

import org.junit.Test;

/**
 * @author jburbrid
 *
 */
public class GroovyBindingBuilderTest {
	
	private static final String PROP_KEY = "user.home";

	@Test
	public void testGetBinding() {
		Binding binding = GroovyBindingBuilder.getInstance().getBinding();
		assertEquals(binding.getProperty(PROP_KEY), System.getProperty(PROP_KEY));
	}

}
