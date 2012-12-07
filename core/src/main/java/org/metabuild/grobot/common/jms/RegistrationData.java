package org.metabuild.grobot.common.jms;

import java.io.Serializable;

/**
 * @author jburbridge
 * @since 11/18/2012
 */
public class RegistrationData implements Serializable {
	
	private static final long serialVersionUID = 2249053693249313248L;
	private String key;
	private String hostname;
	private String address;
	
	/**
	 * @param hostname
	 * @param address
	 */
	public RegistrationData(String key, String hostname, String address) {
		this.key = key;
		this.hostname = hostname;
		this.address = address;
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistrationData [key=").append(key)
				.append(", hostname=").append(hostname).append(", address=")
				.append(address).append("]");
		return builder.toString();
	}
}
