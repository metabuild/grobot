/**
 * 
 */
package org.metabuild.grobot.tasks.groovy;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import groovy.lang.Binding;

import org.junit.Test;
import org.metabuild.grobot.tasks.BindingProvider;
import org.metabuild.grobot.tasks.groovy.GroovyBindingProvider;

/**
 * @author jburbrid
 * @since 9/27/2012
 */
public class GroovyBindingProviderTest {
	
	private static final String PROP_KEY = "key";
	private static final String PROP_VALUE = "value";

	@Test
	public void test() {
		Map<Object, Object> paramMap = new HashMap<Object,Object>();
		paramMap.put(PROP_KEY, PROP_VALUE);
		BindingProvider bindingProvider = new GroovyBindingProvider(paramMap, new Binding());
		assertEquals(bindingProvider.getBinding().getProperty(PROP_KEY), PROP_VALUE);
	}

}
