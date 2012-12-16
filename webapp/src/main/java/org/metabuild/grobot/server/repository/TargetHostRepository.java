package org.metabuild.grobot.server.repository;

import java.util.List;

import org.metabuild.grobot.common.domain.TargetHost;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jburbridge
 * @since 12/15/2012
 */
public interface TargetHostRepository  extends CrudRepository<TargetHost, String> {

	public List<TargetHost> findByName(String name);
	
	public List<TargetHost> findAll();
	
	public TargetHost findById(String id);
}
