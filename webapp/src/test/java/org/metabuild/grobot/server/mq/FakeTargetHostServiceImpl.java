package org.metabuild.grobot.server.mq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.server.service.TargetHostService;

/**
 * @author jburbridge
 * @since 11/17/2012
 */
public class FakeTargetHostServiceImpl implements TargetHostService {
	
	private final Map<String,TargetHost> targetHosts = new HashMap<String, TargetHost>();

	@Override
	public List<TargetHost> findAll() {
		return new ArrayList<TargetHost>(targetHosts.values());
	}

	/*
	 * Creates new target hosts on the fly if their names start with "valid" and adds them to 
	 * the cache, otherwise return null
	 */
	@Override
	public TargetHost findByName(String hostname) {
		TargetHost target = null;
		if (targetHosts.containsKey(hostname)) {
			target = targetHosts.get(hostname);
		} else if (hostname != null && hostname.startsWith("valid")) {
			target = new TargetHost(hostname, hostname + ".fake.address", true);
			targetHosts.put(hostname, target);
		} 
		return target;
	}

	@Override
	public List<TargetHost> findAllWithProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TargetHost find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TargetHost save(TargetHost targetHost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TargetHost targetHost) {
		// TODO Auto-generated method stub

	}

}
