package org.metabuild.grobot.server.service;

import java.util.List;

import org.metabuild.grobot.common.domain.TargetHost;

/**
 * @author jburbridge
 * @since 11/16/2012
 */
public interface TargetHostService {

	public List<TargetHost> findAll();
	
	public List<TargetHost> findAllWithProperties();
	
	public TargetHost findByName(String name);

	public TargetHost find(String id);
	
	public TargetHost save(TargetHost targetHost);
	
	public void delete(TargetHost targetHost);

}
