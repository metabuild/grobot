package org.metabuild.grobot.client.mq;

import java.net.UnknownHostException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author jburbridge
 *
 */
public class HeartbeatProducerImpl implements HeartbeatProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatProducerImpl.class);
	private final HostnameResolver hostnameResolver; 
	
	protected JmsTemplate jmsTemplate;

	public HeartbeatProducerImpl() throws UnknownHostException {
		this.hostnameResolver = new HostnameResolver();
	}
	
	/**
	 * Constructor with DI for unit testing
	 * @param hostnameResolver
	 */
	protected HeartbeatProducerImpl(HostnameResolver hostnameResolver, JmsTemplate jmsTemplate) {
		this.hostnameResolver = hostnameResolver;
		this.jmsTemplate = jmsTemplate;
	}
	
	@Override
	public void sendHeartbeat() throws JMSException {
		final String ping = "PING";
		final String hostname = hostnameResolver.getHostname();
		final MessageCreator messageCreator = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(ping);
				textMessage.setStringProperty("hostname", hostname);
				LOGGER.info("Sending heartbeat ping to grobot master for {}", hostname);
				return textMessage;
			}
			
		};
		jmsTemplate.send(messageCreator);
	}
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
