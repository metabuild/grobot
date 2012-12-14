package org.metabuild.grobot.common.domain;

import java.util.HashMap;
import java.util.Map;

import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.common.domain.TargetHostCacheImpl;

/**
 * Bootstraps a map of fake/dead targets for testing purposes
 * 
 * @author jburbridge
 * @since 10/20/2012
 */
public class FakeTargetHostCacheImpl extends TargetHostCacheImpl {

	private static final Map<String, TargetHost> targets = new HashMap<String, TargetHost>();

	public FakeTargetHostCacheImpl(int numberOfTargets) {
		super(generateTargets(numberOfTargets));
	}
	
	private static Map<String, TargetHost> generateTargets(int numberOfTargets) {
		int x = 0;
		while (x++ < numberOfTargets) {
			TargetHost targetHost = new TargetHost("fakehostname" + x, "fake.host.address" + x, false);
			targets.put(targetHost.getName(), targetHost);
		}
		return targets;
	}
}
