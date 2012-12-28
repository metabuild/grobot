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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import groovy.lang.Script;

import org.junit.Test;
import org.metabuild.grobot.scripts.ScriptWrapper;
import org.metabuild.grobot.scripts.groovy.GroovyScript;

/**
 * @author jburbrid
 * @since 9/27/2012
 */
public class GroovyScriptWrapperTest {

	@Test
	public void testTask() {
		Script script = mock(Script.class);
		when(script.run()).thenReturn("OK!");
		ScriptWrapper scriptWrapper = new GroovyScript(script);
		assertEquals(0, scriptWrapper.getTimesRun());
		assertFalse(scriptWrapper.hasRun());
		assertEquals("OK!", scriptWrapper.run());
		assertEquals(1, scriptWrapper.getTimesRun());
		assertTrue(scriptWrapper.hasRun());
	}
}
