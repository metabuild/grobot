package org.metabuild.grobot.client.mq;

import java.net.UnknownHostException;
import java.util.HashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.metabuild.grobot.mq.PingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author jburbridge
 *
 */
public class DefaultPingResponseProducer implements PingResponseProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPingResponseProducer.class);
	private final HostnameResolver hostnameResolver; 
	
	protected JmsTemplate jmsTemplate;

	public DefaultPingResponseProducer() throws UnknownHostException {
		this.hostnameResolver = new HostnameResolver();
	}
	
	/**
	 * Constructor with DI for unit testing
	 * @param hostnameResolver
	 */
	protected DefaultPingResponseProducer(HostnameResolver hostnameResolver, JmsTemplate jmsTemplate) {
		this.hostnameResolver = hostnameResolver;
		this.jmsTemplate = jmsTemplate;
	}
	
	@Override
	public void sendPingResponse() throws JMSException {
		final String hostname = hostnameResolver.getHostname();
		final PingResponse pingResponse = new PingResponse(hostname, System.getenv(), new HashMap<String,String>());
		final MessageCreator messageCreator = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(pingResponse);
				objectMessage.setStringProperty("hostname", pingResponse.getHostname());
				LOGGER.info("Sending ping response to message queue for {}", pingResponse.getHostname());
				return objectMessage;
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
