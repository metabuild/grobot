package org.metabuild.grobot.server.registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.common.jms.RegistrationData;
import org.metabuild.grobot.server.service.TargetHostService;

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
	public void handleRegistrationRequest(RegistrationData registrationDetails) {
		final String hostname = registrationDetails.getHostname();
		final String clientUuid = registrationDetails.getId();
		LOGGER.info("Handling registration request from {} with id {}", hostname, clientUuid);
		TargetHost target = targetHostService.findByName(hostname);
		if (target == null) {
			// if the target hasn't been registered, save a new record so that it can be 
			target = new TargetHost(hostname,hostname,true);
			// review by an administrator and authorized it if appropriate
			LOGGER.info("No record found for {} TargetHost, creating new unregistered record", hostname);
			targetHostService.save(target);
		} else {
			// check for credentials
			// and send the registration response
			LOGGER.info("Found registered TargetHost for {}, sending registration response", hostname);
			registrationResponseProducer.sendRegistrationResponse(hostname);
		}
	}
}
