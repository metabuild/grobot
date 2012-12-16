package org.metabuild.grobot.server.repository;

import java.util.List;

import org.metabuild.grobot.common.domain.TargetGroup;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jburbridge
 * @since 12/15/2012
 */
public interface TargetGroupRepository extends CrudRepository<TargetGroup, String> {

	public List<TargetGroup> findAll();
	
	public List<TargetGroup> findByName(String name);
	
	public List<TargetGroup> findByActive(boolean active);
	
}
