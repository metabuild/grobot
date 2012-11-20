package org.metabuild.grobot.server.registration;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;

import org.metabuild.grobot.domain.TargetHost;
import org.metabuild.grobot.server.service.TargetHostService;

public class RegistrationServiceImpl implements RegistrationService {

	private TargetHostService targetHostService;
	private RegistrationResponseProducer registrationResponseProducer;
	
	/**
	 * @param targetHostService
	 * @param registrationResponseProducer
	 */
	public RegistrationServiceImpl(TargetHostService targetHostService,
			RegistrationResponseProducer registrationResponseProducer) {
		this.targetHostService = targetHostService;
		this.registrationResponseProducer = registrationResponseProducer;
	}

	@Override
	public void createUnregistered(TargetHost targetHost) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<TargetHost> getPendingRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleRegistrationRequest(Message message) throws JMSException {
		final String hostname = message.getStringProperty("hostname");
		final String clientUuid = message.getStringProperty("client-uuid");
		TargetHost target = targetHostService.findByName(hostname);
		if (target == null) {
			// create the unregistered target host
		} else {
			// check for credentials
			// and send the registration response
			registrationResponseProducer.sendRegistrationResponse(message.getJMSReplyTo());
		}
		
	}
}
