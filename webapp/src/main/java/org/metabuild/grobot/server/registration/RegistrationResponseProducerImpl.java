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
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.metabuild.grobot.common.jms.RegistrationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.stereotype.Component;

/**
 * @author jburbridge
 * @since 11/17/2012
 */
@Component
public class RegistrationResponseProducerImpl  extends JmsGatewaySupport implements RegistrationResponseProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationResponseProducerImpl.class);
	private final long timeToLive = 10000L;

	/*
	 * (non-Javadoc)
	 * @see org.metabuild.grobot.server.mq.RegistrationResponseProducer#sendRegistrationResponse(javax.jms.Destination)
	 */
	@Override
	public void sendRegistrationResponse(final RegistrationData registrationData, final Destination registrationResponseQeue) {
//		getJmsTemplate().setTimeToLive(timeToLive);
		// TODO: java.lang.IllegalStateException: No 'defaultDestination' or 'defaultDestinationName' specified. Check configuration of JmsTemplate.
		getJmsTemplate().send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage(registrationData);
				message.setJMSDestination(registrationResponseQeue);
				message.setStringProperty("recipient-hostname", registrationData.getHostname());
				LOGGER.info("Sending registration response for {} to {}", registrationData.getHostname(), registrationResponseQeue);
				return message;
			}
		});
	}
}
