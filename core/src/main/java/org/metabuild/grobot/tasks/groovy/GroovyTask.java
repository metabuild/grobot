package org.metabuild.grobot.tasks.groovy;

import java.util.concurrent.atomic.AtomicInteger;

import org.metabuild.grobot.tasks.Task;

import groovy.lang.Script;

/**
 * A wrapper class for groovy.lang.Script
 * 
 * @author jburbrid
 * @since 9/28/2012
 */
public class GroovyTask implements Task {

	private final Script script;
	private final AtomicInteger timesRuns = new AtomicInteger();
	
	public GroovyTask(Script script) {
		this.script = script;
	}
	
	public boolean hasRun() {
		return getTimesRun() > 0;
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

