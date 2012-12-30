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
package org.metabuild.grobot.client;

import javax.jms.JMSException;

import org.metabuild.grobot.client.key.KeyManager;
import org.metabuild.grobot.client.registration.RegistrationRequestProducer;
import org.metabuild.grobot.common.jms.RegistrationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
public class GrobotClient implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(GrobotClient.class);
	private final ApplicationContext context;
	private final String hostname;
	private final String address;

	/**
	 * @param name
	 * @param context
	 */
	public GrobotClient(ApplicationContext context, String name, String address) {
		this.hostname = name;
		this.context = context;
		this.address = address;
	}

	/**
	 * Start the RegistrationResponseListener and send a RegistrationRequest to the Grobot master
	 */
	@Override
	public void run() {
		try {
			LOGGER.info("Starting {}'s registrationResponseListenerContainer...", hostname);
			final DefaultMessageListenerContainer registrationResponseListenerContainer = (DefaultMessageListenerContainer) context.getBean("registrationResponseListenerContainer");
			registrationResponseListenerContainer.start();
			LOGGER.info("Now listening for registration response on {}...", registrationResponseListenerContainer.getDestination());

			LOGGER.info("Loading key for {}...", hostname);
			final KeyManager keyManager = (KeyManager) context.getBean("keyManager");

			LOGGER.info("Attempting to register {} with Grobot master...", hostname);
			final RegistrationRequestProducer registrationRequestProducer = (RegistrationRequestProducer) context.getBean("registrationRequestProducer");
			registrationRequestProducer.sendRegistrationRequest(new RegistrationData(keyManager.loadKey(), hostname, address));

		} catch (JMSException e) {
			LOGGER.error("Grobot could not register with the server - is the Grobot server running?", e);
		}
	}
}
