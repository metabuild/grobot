package org.metabuild.grobot.server.registration;

import java.util.UUID;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

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
	public void sendRegistrationResponse(final Destination replyToDestination) {
		getJmsTemplate().setTimeToLive(timeToLive);
		getJmsTemplate().send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createMessage();
				message.setJMSDestination(replyToDestination);
				message.setStringProperty("serverkey",UUID.randomUUID().toString());
				LOGGER.debug("Sending registration response to {}", replyToDestination.toString());
				return message;
			}
		});
	}
}
