package org.metabuild.grobot.common.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author jburbridge
 * @since 10/13/2012
 */
@Component
public class TargetHostCacheImpl implements TargetHostCache {

	private final Map<String,TargetHost> targetMap;

	/**
	 * Default constructor
	 */
	public TargetHostCacheImpl() {
		this(Collections.synchronizedMap(new HashMap<String,TargetHost>()));
	}
	
	/**
	 * @param targetMap
	 */
	protected TargetHostCacheImpl(Map<String, TargetHost> targetMap) {
		this.targetMap = targetMap;
	}
	
	public TargetHost get(String targetName) {
		return targetMap.get(targetName);
	}
	
	public void put(String targetName, TargetHost target) {
		targetMap.put(targetName, target);
	}
	
	public int size() {
		return targetMap.size();
	}

	public List<TargetHost> getAll() {
		return (List<TargetHost>) new ArrayList<TargetHost>(targetMap.values());
	}
}
