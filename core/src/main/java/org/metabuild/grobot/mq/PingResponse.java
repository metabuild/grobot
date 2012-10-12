package org.metabuild.grobot.mq;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A response to a ping request from the Grobot server, containing system details about the target host
 * 
 * @author jburbridge
 * @since 10/11/2012
 */
public class PingResponse implements Serializable {

	private static final long serialVersionUID = 4305522797844648204L;
	
	private final String hostname;
	private final Map<String,String> systemProperties;
	private final Map<String,String> otherProperties;

	/**
	 * Default constructor, sets the hostname to "localhost" and creates empty maps
	 */
	public PingResponse() {
		this("localhost", Collections.synchronizedMap(new HashMap<String,String>()),
				Collections.synchronizedMap(new HashMap<String,String>()));
	}
	/**
	 * @param hostname
	 * @param systemProperties
	 * @param otherProperties
	 */
	public PingResponse(String hostname, Map<String, String> systemProperties, Map<String, String> otherProperties) {
		this.hostname = hostname;
		this.systemProperties = systemProperties;
		this.otherProperties = otherProperties;
	}
	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}
	/**
	 * @return the systemProperties
	 */
	public Map<String, String> getSystemProperties() {
		return systemProperties;
	}
	/**
	 * @return the otherProperties
	 */
	public Map<String, String> getOtherProperties() {
		return otherProperties;
	}
	
}
