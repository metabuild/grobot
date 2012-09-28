package org.metabuild.grobot.core;

import java.util.concurrent.atomic.AtomicInteger;

import groovy.lang.Script;

/**
 * A wrapper class for groovy.lang.Script
 * 
 * @author jburbrid
 * @since 9/28/2012
 */
public class Task {

	private final Script script;
	private final AtomicInteger timesRuns = new AtomicInteger();
	
	public Task(Script script) {
		this.script = script;
	}
	
	public boolean hasRun() {
		return timesRuns.intValue() > 0;
	}
	
	public Object run() {
		timesRuns.incrementAndGet();
		return script.run();
	}
	
	public int getTimesRun() {
		return timesRuns.get();
	}
	
	@Override
	public String toString() {
		return this.script.getClass().getCanonicalName();
	}
}

