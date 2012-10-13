/**
 * 
 */
package org.metabuild.grobot.server.mq;

import java.net.UnknownHostException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.metabuild.net.HostnameResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

/**
 * Allows the grobot server to a simple ping request to the message queue's ping topic. Subscribers 
 * to the topic should then respond with a ping response containing their hostnames and system properties.
 * 
 * @author jburbridge
 * @since 10/12/2012
 */
public class PingRequestProducerImpl extends JmsGatewaySupport implements PingRequestProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingRequestProducerImpl.class);
	private final HostnameResolver hostnameResolver; 
	private final long timeToLive = 10000L;

	/**
	 * Default constructor
	 * @throws UnknownHostException 
	 */
	public PingRequestProducerImpl() throws UnknownHostException {
		this.hostnameResolver = new HostnameResolver();
	}

	/**
	 * Constructor with DI for unit testing
	 * 
	 * @param hostnameResolver a utility that looks up the local hostname
	 */
	protected PingRequestProducerImpl(HostnameResolver hostnameResolver) {
		this.hostnameResolver = hostnameResolver;
	}
	
	/* (non-Javadoc)
	 * @see org.metabuild.grobot.server.mq.PingRequestProducer#sendPingRequest()
	 */
	@Override
	public void sendPingRequest() throws JMSException {
		final String ping = "PING";
		final String hostname = hostnameResolver.getHostname();
		getJmsTemplate().setTimeToLive(timeToLive);
		getJmsTemplate().send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(ping);
				textMessage.setStringProperty("ping","request");
				textMessage.setStringProperty("hostname", hostname);
				LOGGER.info("Sending \"{}\" request to message topic", ping);
				return textMessage;
			}
		});
	}
}
