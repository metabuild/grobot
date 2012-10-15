/**
 * 
 */
package org.metabuild.grobot.server.mq;

import java.net.UnknownHostException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.metabuild.grobot.mq.StatusMessageType;
import org.metabuild.net.HostnameResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

/**
 * Allows the grobot server to a simple status request to the message queue's status topic. Subscribers 
 * to the topic should then respond with a status response containing their hostnames and system properties.
 * 
 * @author jburbridge
 * @since 10/12/2012
 */
public class StatusRequestProducerImpl extends JmsGatewaySupport implements StatusRequestProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusRequestProducerImpl.class);
	private final HostnameResolver hostnameResolver; 
	private final long timeToLive = 10000L;

	/**
	 * Default constructor
	 * @throws UnknownHostException 
	 */
	public StatusRequestProducerImpl() throws UnknownHostException {
		this.hostnameResolver = new HostnameResolver();
	}

	/**
	 * Constructor with DI for unit testing
	 * 
	 * @param hostnameResolver a utility that looks up the local hostname
	 */
	protected StatusRequestProducerImpl(HostnameResolver hostnameResolver) {
		this.hostnameResolver = hostnameResolver;
	}
	
	/* (non-Javadoc)
	 * @see org.metabuild.grobot.server.mq.StatusRequestProducer#sendPingRequest()
	 */
	@Override
	public void sendStatusRequest() throws JMSException {
		final String status = "ALIVE";
		final String hostname = hostnameResolver.getHostname();
//		getJmsTemplate().setTimeToLive(timeToLive);
		getJmsTemplate().send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(status);
				textMessage.setStringProperty(StatusMessageType.class.getSimpleName(),StatusMessageType.REQUEST.getType());
				textMessage.setStringProperty("hostname", hostname);
				LOGGER.info("Sending \"{}\" request to message topic", status);
				return textMessage;
			}
		});
	}
}
