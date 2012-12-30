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
package org.metabuild.grobot.client.status;

import java.net.UnknownHostException;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.metabuild.grobot.common.jms.StatusMessageType;
import org.metabuild.grobot.common.jms.StatusResponse;
import org.metabuild.net.HostnameResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
public class StatusResponseProducerImpl extends JmsGatewaySupport implements StatusResponseProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusResponseProducerImpl.class);
	private final String hostname;

	/**
	 *  Default constructor
	 * @throws UnknownHostException
	 */
	public StatusResponseProducerImpl() throws UnknownHostException {
		this(new HostnameResolver());
	}

	/**
	 * Constructor with DI for unit testing
	 * 
	 * @param hostname
	 */
	public StatusResponseProducerImpl(String hostname) {
		this.hostname = hostname;
	}


	/**
	 * Constructor with DI for unit testing
	 * 
	 * @param hostnameResolver
	 */
	protected StatusResponseProducerImpl(HostnameResolver hostnameResolver) {
		this.hostname = hostnameResolver.getHostname();
	}

	/*
	 * (non-Javadoc)
	 * @see org.metabuild.grobot.client.mq.StatusResponseProducer#sendStatusResponse()
	 */
	@Override
	public void sendStatusResponse() throws JMSException {
		// identify the sender given the hostname
		sendStatusResponseAs(hostname);
	}

	/**
	 * Wrapped message producer method so we can impersonate different hostnames
	 * 
	 * @param hostname
	 */
	public void sendStatusResponseAs(String hostname) {
		final StatusResponse statusResponse = new StatusResponse(hostname, System.getProperties(), new Properties());
		getJmsTemplate().send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(statusResponse);
				objectMessage.setStringProperty(StatusMessageType.class.getSimpleName(),StatusMessageType.RESPONSE.getType());
				objectMessage.setStringProperty("hostname", statusResponse.getHostname());
				LOGGER.info("Sending StatusMessageType response to message queue for {}", statusResponse.getHostname());
				return objectMessage;
			}
		});
	}
}
