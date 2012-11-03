package org.metabuild.grobot.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jburbrid
 * @since 9/27/2012
 */
public class TargetGroup implements Targetable {

	private String name;
	private List<Targetable> targets;
	private TargetGroup parent;
	private boolean active;

	/**
	 * @param name - the group's name
	 */
	public TargetGroup(String name) {
		this.name = name;
		this.targets = Collections.synchronizedList(new ArrayList<Targetable>());
		this.active = true;
		targets.add(this);
	}

	/**
	 * @param name - the group's name
	 * @param targets - a list of sub targets (groups or hosts)
	 * @param parent - a parent group
	 */
	public TargetGroup(String name, List<Targetable> targets, TargetGroup parent) {
		this.name = name;
		this.targets = targets;
		this.parent = parent;
	}

	/**
	 * @param name - the group's name
	 * @param targets - a list of sub targets (groups or hosts)
	 */
	public TargetGroup(String name, List<Targetable> targets) {
		this.name = name;
		this.targets = targets;
	}

	/**
	 * @param targetable
	 */
	public void addTarget(Targetable targetable) {
		addTargets(targetable.getTargets());
	}
	
	/**
	 * @param targetables
	 */
	public void addTargets(List<Targetable> targetables) {
		this.targets.addAll(targetables);
	}
	
	/* (non-Javadoc)
	 * @see org.metabuild.grobot.domain.Target#getTargets()
	 */
	@Override
	public List<Targetable> getTargets() {
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
	 * @param targetables the targets to set
	 */
	public void setTargets(List<Targetable> targetables) {
		this.targets = targetables;
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
