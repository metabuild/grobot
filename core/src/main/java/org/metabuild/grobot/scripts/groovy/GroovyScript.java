package org.metabuild.grobot.scripts.groovy;

import groovy.lang.Script;

import java.util.concurrent.atomic.AtomicInteger;

import org.metabuild.grobot.scripts.ScriptWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A wrapper class for groovy.lang.Script
 * 
 * @author jburbrid
 * @since 9/28/2012
 */
public class GroovyScript implements ScriptWrapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(GroovyScript.class);
	
	private final Script script;
	private final AtomicInteger timesRuns = new AtomicInteger();
	private String md5Hash;
	
	public GroovyScript(Script script) {
		this.script = script;
	}
	
	@Override
	public String getName() {
		return this.script.getClass().getName();
	}

	@Override
	public boolean hasRun() {
		return getTimesRun() > 0;
	}
	
	@Override
	public Object run() {
		LOGGER.debug("Running task {} for the {}th time", toString(), getTimesRun());
		timesRuns.incrementAndGet();
		return script.run();
	}
	
	@Override
	public int getTimesRun() {
		return timesRuns.get();
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public String getHash() {
		return md5Hash;
	}
	
	/**
	 * @param md5Hash the md5Hash to set
	 */
	public void setHash(String md5Hash) {
		this.md5Hash = md5Hash;
	}
}

