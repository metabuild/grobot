package org.metabuild.grobot.client;

import groovy.util.GroovyScriptEngine;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author jburbridge
 *
 */
public class GrobotTaskFactoryTest {

	@Test
	public void testGrobotTaskFactory() throws IOException {
		GrobotTaskFactory factory = new GrobotTaskFactory("foo", new GroovyScriptEngine("foo"));
		Assert.assertNotNull(factory);
	}

	@Test
	public void testGetTasks() throws IOException {
		GrobotTaskFactory factory = new GrobotTaskFactory("foo", new GroovyScriptEngine("foo"));
		Assert.assertNotNull(factory.getTasks());
	}

	@Test
	public void testGetFiles() throws IOException {
		GrobotTaskFactory factory = new GrobotTaskFactory("foo", new GroovyScriptEngine("foo"));
		Assert.assertNotNull(factory.getFiles(new File("foo")));
	}

	@Test
	public void testGetBinding() throws IOException {
		GrobotTaskFactory factory = new GrobotTaskFactory("foo", new GroovyScriptEngine("foo"));
		Assert.assertNotNull(factory.getBinding());
	}

}
