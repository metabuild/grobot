package org.metabuild.grobot.server.registration;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;

import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.server.service.TargetHostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);
			
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
		LOGGER.info("Got registration request from {} with id {}", hostname, clientUuid);
		TargetHost target = targetHostService.findByName(hostname);
		if (target == null) {
			// if the target hasn't been registered, save a new record so that it can be 
			// review by an administrator and authorized it if appropriate
			targetHostService.save(target);
		} else {
			// check for credentials
			// and send the registration response
			registrationResponseProducer.sendRegistrationResponse(message.getJMSReplyTo());
		}
		
	}
}
