package org.metabuild.grobot.server.mq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;

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
	public RegistrationData processRegistrationRequest(RegistrationData registrationDetails, Destination replDestination) {
		final TargetHost targetHost = new TargetHost(registrationDetails.getHostname(), registrationDetails.getHostname(), true);
		createUnregistered(targetHost);
		registrationDetails.setKey(targetHost.getId());
		return registrationDetails;
	}
}
