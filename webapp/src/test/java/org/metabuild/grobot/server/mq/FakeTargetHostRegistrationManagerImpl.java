package org.metabuild.grobot.server.mq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.common.jms.RegistrationData;
import org.metabuild.grobot.server.registration.RegistrationService;

public class FakeTargetHostRegistrationManagerImpl implements
		RegistrationService {

	private static final Map<String,TargetHost> targetHosts = new HashMap<String,TargetHost>();
	
	protected void createUnregistered(TargetHost targetHost) {
		targetHosts.put(targetHost.getName(), targetHost);
	}

	protected List<TargetHost> getPendingRegistrations() {
		return new ArrayList<TargetHost>(targetHosts.values());
	}

	@Override
	public void handleRegistrationRequest(RegistrationData registrationDetails) {
		createUnregistered(new TargetHost(registrationDetails.getHostname(), registrationDetails.getHostname(), true));
	}
}
