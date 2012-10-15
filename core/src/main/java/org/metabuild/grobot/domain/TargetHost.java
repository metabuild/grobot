package org.metabuild.grobot.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.metabuild.grobot.mq.StatusResponse;

/**
 * @author jburbrid
 * @since 9/27/2012
 */
public class TargetHost implements Target {
	
	private String name;
	private String address;
	private boolean active;
	private Properties systemProperties;
	private Properties otherProperties;
	private List<Target> targets;

	/**
	 * Default constructor - initializes the object with empty properties
	 * 
	 * @param name
	 * @param address - the fully qualified host name
	 * @param active - is the host available for targeting
	 */
	public TargetHost(String name, String address, boolean active) {
		this.name = name;
		this.address = address;
		this.active = active;
		this.systemProperties = new Properties();
		this.otherProperties = new Properties();
		this.targets = new ArrayList<Target>();
		targets.add(this);
	}
	
	/**
	 * Constructor for creating TargetHost from PingResponses
	 * 
	 * @param statusResponse
	 */
	public TargetHost(StatusResponse statusResponse) {
		this.name = statusResponse.getHostname();
		this.address = statusResponse.getHostname();
		this.systemProperties = statusResponse.getSystemProperties();
		this.otherProperties = statusResponse.getOtherProperties();
		this.active = true;
	}

	/* (non-Javadoc)
	 * @see org.metabuild.grobot.domain.Target#getTargets()
	 */
	@Override
	public List<Target> getTargets() {
		return targets;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the systemProperties
	 */
	public Properties getSystemProperties() {
		return systemProperties;
	}

	/**
	 * @param systemProperties the systemProperties to set
	 */
	public void setSystemProperties(Properties systemProperties) {
		this.systemProperties = systemProperties;
	}

	/**
	 * @return the otherProperties
	 */
	public Properties getOtherProperties() {
		return otherProperties;
	}

	/**
	 * @param otherProperties the otherProperties to set
	 */
	public void setOtherProperties(Properties otherProperties) {
		this.otherProperties = otherProperties;
	}

	@Override
	public String toString() {
		return name;
	}
}
