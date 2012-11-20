/**
 * 
 */
package org.metabuild.grobot.common.jms;

/**
 * @author jburbridge
 * @since 10/14/2012
 */
public enum StatusMessageType {
	
	REQUEST("Request"), 
	RESPONSE("Response");
	
	private String type;
	
	StatusMessageType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return type;
	}
}
