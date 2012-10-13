package org.metabuild.grobot.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author jburbridge
 *
 */
@Component
public class TargetCache {

	private final Map<String,Target> targetMap;

	/**
	 * Default constructor
	 */
	public TargetCache() {
		this(Collections.synchronizedMap(new HashMap<String,Target>()));
	}
	
	/**
	 * @param targetMap
	 */
	protected TargetCache(Map<String, Target> targetMap) {
		this.targetMap = targetMap;
	}
	
	public Target get(String targetName) {
		return targetMap.get(targetName);
	}
	
	public void put(String targetName, Target target) {
		targetMap.put(targetName, target);
	}
	
	public int size() {
		return targetMap.size();
	}
}
