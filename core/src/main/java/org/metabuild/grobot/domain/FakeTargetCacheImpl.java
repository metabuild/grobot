package org.metabuild.grobot.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Bootstraps a map of fake targets for testing purposes
 * 
 * @author jburbridge
 * @since 10/20/2012
 */
public class FakeTargetCacheImpl extends TargetCacheImpl {

	public FakeTargetCacheImpl(int numberOfTargets) {
		super(generateTargets(numberOfTargets));
	}
	
	private static Map<String, TargetHost> generateTargets(int numberOfTargets) {
		Map<String, TargetHost> targets = new HashMap<String, TargetHost>();
		int x = 0;
		while (x++ < numberOfTargets) {
			TargetHost targetHost = new TargetHost("fakehostname" + x, "fakehostaddress" + x, true);
			targets.put(targetHost.getName(), targetHost);
		}
		return targets;
	}
}
