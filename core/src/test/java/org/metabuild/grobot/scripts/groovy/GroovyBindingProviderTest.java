/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.metabuild.grobot.scripts.groovy;

import static org.junit.Assert.assertEquals;
import groovy.lang.Binding;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.metabuild.grobot.scripts.BindingProvider;
import org.metabuild.grobot.scripts.groovy.GroovyBindingProvider;

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
