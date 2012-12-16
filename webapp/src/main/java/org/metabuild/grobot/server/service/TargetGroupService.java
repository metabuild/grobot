package org.metabuild.grobot.server.service;

import java.lang.annotation.Target;
import java.util.List;

import org.metabuild.grobot.common.domain.TargetGroup;

/**
 * 
 * @author jburbridge
 * @since 12/16/012
 */
public interface TargetGroupService {

	public List<TargetGroup> findAll();
	
	public TargetGroup find(String id);
	
	public TargetGroup findById(String id);
	
	public TargetGroup findByName(String name);
	
	public TargetGroup save(TargetGroup targetGroup);
	
	public void delete(TargetGroup targetGroup);
	
}
