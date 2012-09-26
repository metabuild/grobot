package org.metabuild.grobot.core;

import groovy.lang.Script;

public class Task extends Script {

	private final Script wrappedScript;
	private int runTimes = 0;
	
	public Task(Script script) {
		this.wrappedScript = script;
	}
	
	public boolean hasRun() {
		return runTimes > 0;
	}
	
	@Override
	public Object run() {
		Object returnValue = wrappedScript.run();
		runTimes++;
		return returnValue;
	}
	
	@Override
	public String toString() {
		return wrappedScript.getClass().getCanonicalName();
	}
}

