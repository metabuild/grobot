package org.metabuild.grobot.server.registration;

import javax.jms.Destination;

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
	public RegistrationData processRegistrationRequest(RegistrationData registrationDetails, Destination replyToDestination) {
		final String hostname = registrationDetails.getHostname();
		final String address = registrationDetails.getAddress();
		final String clientUuid = registrationDetails.getKey();
		LOGGER.info("Handling registration request from {} with id {}", hostname, clientUuid);
		TargetHost target = targetHostService.findByName(hostname);
		if (target == null) {
			// if the target hasn't been registered, save a new record so that it can be 
			// reviewed by an administrator and authorized it if appropriate
			LOGGER.info("No record found for {} TargetHost, creating new unregistered record", hostname);
			if (hostname != null) {
				target = targetHostService.save(new TargetHost(hostname,address,true));
				registrationDetails.setKey(target.getId());
			} else {
				LOGGER.warn("Can't create a new TargetHost with a null hostname");
				// TODO: need to figure out how to notify the system's administrator
			}
		} else {
			// TODO: check for credentials
			// and send the registration response
			registrationDetails.setKey(target.getId());
			LOGGER.info("Found registered TargetHost for {}, sending registration response to {}", hostname, replyToDestination);
			// registrationResponseProducer.sendRegistrationResponse(registrationDetails, replyToDestination);
		}
		return registrationDetails;
	}
}
