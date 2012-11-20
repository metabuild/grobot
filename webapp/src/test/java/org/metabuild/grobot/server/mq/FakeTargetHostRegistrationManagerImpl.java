package org.metabuild.grobot.server.mq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.metabuild.grobot.domain.TargetHost;
import org.metabuild.grobot.server.registration.RegistrationService;

public class FakeTargetHostRegistrationManagerImpl implements
		RegistrationService {

	private static final Map<String,TargetHost> targetHosts = new HashMap<String,TargetHost>();
	
	@Override
	public void createUnregistered(TargetHost targetHost) {
		targetHosts.put(targetHost.getName(), targetHost);
	}

	@Override
	public List<TargetHost> getPendingRegistrations() {
		return new ArrayList<TargetHost>(targetHosts.values());
	}

	@Override
	public void handleRegistrationRequest(Message message) throws JMSException {
		// TODO Auto-generated method stub
		
	}
}
