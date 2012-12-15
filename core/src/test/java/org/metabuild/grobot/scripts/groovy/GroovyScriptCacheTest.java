/**
 * 
 */
package org.metabuild.grobot.scripts.groovy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.metabuild.grobot.scripts.ScriptWrapper;
import org.metabuild.grobot.scripts.groovy.GroovyScript;
import org.metabuild.grobot.scripts.groovy.GroovyScriptCache;
import org.metabuild.grobot.scripts.groovy.GroovyScriptFactory;

/**
 * @author jburbridge
 * @since 10/23/2012
 */
public class GroovyScriptCacheTest {

	/**
	 * Test method for {@link org.metabuild.grobot.scripts.groovy.GroovyScriptCache#GroovyTaskCache(org.metabuild.grobot.scripts.groovy.GroovyScriptFactory, java.util.Map)}.
	 */
	@Test
	public void testGroovyScriptCacheEmptyMap() {
		assertNotNull(new GroovyScriptCache(getMockScriptFactory(), new HashMap<String, GroovyScript>()));
	}

	/**
	 * Test method for {@link org.metabuild.grobot.scripts.groovy.GroovyScriptCache#get(java.lang.String)}.
	 */
	@Test
	public void testGetScriptWithMatch() {
		GroovyScriptCache scriptCache = new GroovyScriptCache(getMockScriptFactory(), new HashMap<String, GroovyScript>());
		assertNotNull(scriptCache.get("MyMockScriptHash"));
		assertEquals("MyMockScriptName",scriptCache.get("MyMockScriptHash").toString());
		assertNull(scriptCache.get(null));
	}

	public static GroovyScriptFactory getMockScriptFactory() {
		
		GroovyScriptFactory scriptFactory = mock(GroovyScriptFactory.class);
		GroovyScript script = mock(GroovyScript.class);
		when(script.getHash()).thenReturn("MyMockScriptHash");
		when(script.toString()).thenReturn("MyMockScriptName");
		List<ScriptWrapper> scripts = new ArrayList<ScriptWrapper>();
		scripts.add(script);
		when(scriptFactory.getScripts()).thenReturn(scripts);
		return scriptFactory;
	}
}
