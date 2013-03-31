/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.metabuild.grobot.server.registration;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.metabuild.grobot.common.domain.Bot;
import org.metabuild.grobot.common.jms.RegistrationData;
import org.metabuild.grobot.server.service.BotService;

public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);
			
	private BotService targetHostService;
	private RegistrationResponseProducer registrationResponseProducer;
	
	/**
	 * @param targetHostService
	 * @param registrationResponseProducer
	 */
	public RegistrationServiceImpl(BotService targetHostService,
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
		Bot target = targetHostService.findByName(hostname);
		if (target == null) {
			// if the target hasn't been registered, save a new record so that it can be 
			// reviewed by an administrator and authorized it if appropriate
			LOGGER.info("No record found for {} TargetHost, creating new unregistered record", hostname);
			if (hostname != null) {
				target = targetHostService.create(new Bot(hostname,address,true));
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
