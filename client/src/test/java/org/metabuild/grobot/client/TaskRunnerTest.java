package org.metabuild.grobot.client;

import groovy.util.GroovyScriptEngine;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author jburbridge
 *
 */
public class GrobotTaskRunnerTest {

	/**
	 * Test method for {@link org.metabuild.grobot.client.GrobotTaskRunner#GrobotTaskRunner(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	public void testGrobotTaskRunnerOK() throws IOException {
		GrobotTaskRunner runner = new GrobotTaskRunner("foo");
		Assert.assertNotNull(runner);
		Assert.assertNotNull(runner.getTasksDir());
		Assert.assertNotNull(runner.getGrobotTaskFactory());
		Assert.assertNotNull(runner.getGroovyScriptEngine());
	}

	/**
	 * Test method for {@link org.metabuild.grobot.client.GrobotTaskRunner#GrobotTaskRunner(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test(expected=NullPointerException.class)
	public void testGrobotTaskRunnerFail() throws IOException {
		new GrobotTaskRunner(null);
	}

	/**
	 * Test method for {@link org.metabuild.grobot.client.GrobotTaskRunner#GrobotTaskRunner(java.lang.String, groovy.util.GroovyScriptEngine)}.
	 * @throws IOException 
	 */
	@Test
	public void testGrobotTaskRunnerStringGroovyScriptEngineOK() throws IOException {
		GrobotTaskRunner runner = new GrobotTaskRunner("foo", new GroovyScriptEngine("foo"));
		Assert.assertNotNull(runner);
		Assert.assertNotNull(runner.getTasksDir());
		Assert.assertNotNull(runner.getGrobotTaskFactory());
		Assert.assertNotNull(runner.getGroovyScriptEngine());
	}
}
