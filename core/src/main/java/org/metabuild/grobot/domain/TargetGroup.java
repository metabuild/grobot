package org.metabuild.grobot.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jburbrid
 * @since 9/27/2012
 */
public class TargetGroup implements Target {

	private String name;
	private List<Target> targets;
	private TargetGroup parent;
	private boolean active;

	/**
	 * @param name - the group's name
	 */
	public TargetGroup(String name) {
		this.name = name;
		this.targets = Collections.synchronizedList(new ArrayList<Target>());
		this.active = true;
		targets.add(this);
	}

	/**
	 * @param name - the group's name
	 * @param targets - a list of sub targets (groups or hosts)
	 * @param parent - a parent group
	 */
	public TargetGroup(String name, List<Target> targets, TargetGroup parent) {
		this.name = name;
		this.targets = targets;
		this.parent = parent;
	}

	/**
	 * @param name - the group's name
	 * @param targets - a list of sub targets (groups or hosts)
	 */
	public TargetGroup(String name, List<Target> targets) {
		this.name = name;
		this.targets = targets;
	}

	/**
	 * @param target
	 */
	public void addTarget(Target target) {
		addTargets(target.getTargets());
	}
	
	/**
	 * @param targets
	 */
	public void addTargets(List<Target> targets) {
		this.targets.addAll(targets);
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
	 * @return the parent
	 */
	public TargetGroup getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(TargetGroup parent) {
		this.parent = parent;
	}

	/**
	 * @param targets the targets to set
	 */
	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean isActive() {
		return active;
	}
}
