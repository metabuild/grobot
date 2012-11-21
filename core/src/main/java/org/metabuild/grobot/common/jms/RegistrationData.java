package org.metabuild.grobot.common.jms;

import java.io.Serializable;

/**
 * @author jburbridge
 * @since 11/18/2012
 */
public class RegistrationData implements Serializable {

	private static final long serialVersionUID = -4279141064045328506L;
	private String id;
	private String hostname;
	private String address;
	
	/**
	 * @param hostname
	 * @param address
	 */
	public RegistrationData(String hostname, String address) {
		this.hostname = hostname;
		this.address = address;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}
	/**
	 * @param hostname the hostname to set
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
