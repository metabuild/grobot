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

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.metabuild.grobot.common.jms.RegistrationData;
import org.metabuild.grobot.common.jms.RegistrationDataConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Listens for incoming registration requests
 * 
 * @author jburbridge
 * @since 11/17/2012
 */
@Component
public class RegistrationRequestListener  implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationRequestListener.class);

	private final RegistrationDataConverter messageConverter;
	private final RegistrationService registrationService;

	public RegistrationRequestListener(RegistrationService registrationService) {
		LOGGER.info("Initializing {}...", this.getClass().getName());
		this.registrationService = registrationService;
		this.messageConverter = new RegistrationDataConverter();
	}

	@Override
	public void onMessage(Message message) {
		try {
			final RegistrationData registrationDetails = (RegistrationData) messageConverter.fromMessage(message);
			LOGGER.info("Received registration request from {} with uuid {}", registrationDetails.getHostname(), registrationDetails.getKey());
			registrationService.processRegistrationRequest(registrationDetails, message.getJMSReplyTo());
		} catch (JMSException e) {
			LOGGER.error("Error receiving registration request", e);
		}
	}
}
