package org.metabuild.grobot.webapp.controllers;

/**
 * @author jburbridge
 * @since 11/4/2012
 */
public enum NavMenuItems {

//	HOME("home"), 
	BOTS("bots"), 
	TASKS("tasks"), 
	CONFIG("config"), 
	HELP("help");

	private String path;
	
	NavMenuItems(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	@Override
	public String toString() {
		return path;
	}
}
