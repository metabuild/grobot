package org.metabuild.grobot.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jburbrid
 * @since 9/27/2012
 */
public class TargetHost implements Target {
	
	private String name;
	private String address;
	private boolean active;
	private List<Target> targets;

	/**
	 * Default constructor
	 * 
	 * @param name
	 * @param address - the fully qualified host name
	 * @param active - is the host available for targeting
	 */
	public TargetHost(String name, String address, boolean active) {
		this.name = name;
		this.address = address;
		this.active = active;
		this.targets = new ArrayList<Target>();
		targets.add(this);
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
	
	@Override
	public String toString() {
		return name;
	}
}
