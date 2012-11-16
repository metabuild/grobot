package org.metabuild.grobot.server.dao;

import java.util.List;

import org.metabuild.grobot.domain.TargetHost;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jburbridge
 * @since 11/12/2012
 */
@Transactional
public interface TargetHostDao {

	public List<TargetHost> findAll();
	
	public TargetHost find(Long id);
	
	public List<TargetHost> findByName(String name);
	
	public String findNameById(Long id);
	
}
