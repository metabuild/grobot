package org.metabuild.grobot.server.status;

import java.net.UnknownHostException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.metabuild.grobot.common.jms.StatusMessageType;
import org.metabuild.net.HostnameResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.stereotype.Component;

/**
 * Allows the grobot server to a simple status request to the message queue's status topic. Subscribers 
 * to the topic should then respond with a status response containing their hostnames and system properties.
 * 
 * @author jburbridge
 * @since 10/12/2012
 */
@Component("statusRequestProducer")
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
		final String hostname = hostnameResolver.getHostname();
		getJmsTemplate().setTimeToLive(timeToLive);
		getJmsTemplate().send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createMessage();
				message.setStringProperty(StatusMessageType.class.getSimpleName(),StatusMessageType.REQUEST.getType());
				message.setStringProperty("hostname", hostname);
				LOGGER.debug("Sending status request to message topic");
				return message;
			}
		});
	}
}
