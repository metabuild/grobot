package org.metabuild.grobot.domain;

import java.util.List;

/**
 * @author jburbridge
 * @since 10/20/2012
 */
public interface TargetHostCache {

	public TargetHost get(String targetName);
	
	public void put(String targetName, TargetHost target);
	
	public int size();
	
	public List<TargetHost> getAll();
}
