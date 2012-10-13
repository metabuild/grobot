package org.metabuild.grobot.client.mq;

import java.net.UnknownHostException;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.metabuild.grobot.mq.PingResponse;
import org.metabuild.net.HostnameResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
public class PingResponseProducerImpl extends JmsGatewaySupport implements PingResponseProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingResponseProducerImpl.class);
	private final HostnameResolver hostnameResolver; 
	
	/**
	 *  Default constructor
	 * @throws UnknownHostException
	 */
	public PingResponseProducerImpl() throws UnknownHostException {
		this.hostnameResolver = new HostnameResolver();
	}
	
	/**
	 * Constructor with DI for unit testing
	 * 
	 * @param hostnameResolver
	 */
	protected PingResponseProducerImpl(HostnameResolver hostnameResolver) {
		this.hostnameResolver = hostnameResolver;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.metabuild.grobot.client.mq.PingResponseProducer#sendPingResponse()
	 */
	@Override
	public void sendPingResponse() throws JMSException {
		final String hostname = hostnameResolver.getHostname();
		final PingResponse pingResponse = new PingResponse(hostname, System.getProperties(), new Properties());
		getJmsTemplate().send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(pingResponse);
				objectMessage.setStringProperty("ping","response");
				objectMessage.setStringProperty("hostname", pingResponse.getHostname());
				LOGGER.info("Sending ping response to message queue for {}", pingResponse.getHostname());
				return objectMessage;
			}
		});
	}
}
