package org.metabuild.grobot.client.mq;

import java.net.UnknownHostException;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.metabuild.grobot.mq.StatusMessageType;
import org.metabuild.grobot.mq.StatusResponse;
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
	private final HostnameResolver hostnameResolver; 
	
	/**
	 *  Default constructor
	 * @throws UnknownHostException
	 */
	public StatusResponseProducerImpl() throws UnknownHostException {
		this.hostnameResolver = new HostnameResolver();
	}
	
	/**
	 * Constructor with DI for unit testing
	 * 
	 * @param hostnameResolver
	 */
	protected StatusResponseProducerImpl(HostnameResolver hostnameResolver) {
		this.hostnameResolver = hostnameResolver;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.metabuild.grobot.client.mq.StatusResponseProducer#sendStatusResponse()
	 */
	@Override
	public void sendStatusResponse() throws JMSException {
		// identify the sender given the hostname
		sendStatusResponseAs(hostnameResolver.getHostname());
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
