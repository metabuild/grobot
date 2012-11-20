package org.metabuild.grobot.common.domain;

import java.io.Serializable;

/**
 * @author jburbrid
 * @since 10/26/2012
 */
public enum TargetHostStatus implements Serializable {

	STOPPED("Stopped"), 
	IDLE("Idle"), 
	WORKING("Working"), 
	TIMEOUT("Timeout"), 
	INITIALIZING("Initializing");

	private String status;
	
	TargetHostStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return status;
	}
}
