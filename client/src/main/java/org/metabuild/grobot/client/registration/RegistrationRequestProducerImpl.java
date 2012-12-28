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
package org.metabuild.grobot.client.registration;

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
import org.springframework.stereotype.Service;

/**
 * @author jburbridge
 * @since 11/18/2012
 */
@Service("registrationRequestProducer")
public class RegistrationRequestProducerImpl extends JmsGatewaySupport implements RegistrationRequestProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationRequestProducerImpl.class);
	private final Destination replyToDestination;

	/**
	 * Create a RegistrationRequestProducer and set the replyToDestination address to the queue
	 * that the RegistrationResponseProducer should reply to
	 * 
	 * @param replyToDestination
	 */
	public RegistrationRequestProducerImpl(Destination replyToDestination) {
		this.replyToDestination = replyToDestination;
	}

	/*
	 * (non-Javadoc)
	 * @see org.metabuild.grobot.client.mq.RegistrationRequestProducer#sendRegistrationRequest()
	 */
	@Override
	public void sendRegistrationRequest(final RegistrationData registrationData) throws JMSException {
		getJmsTemplate().send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage(registrationData);
				message.setJMSReplyTo(replyToDestination);
				LOGGER.info("Sending registration request for {} to {}", registrationData.getHostname(),
						getJmsTemplate().getDefaultDestination().toString());
				return message;
			}
		});
	}
}
