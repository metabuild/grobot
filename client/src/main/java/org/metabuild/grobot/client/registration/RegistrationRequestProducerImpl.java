package org.metabuild.grobot.client.registration;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.metabuild.grobot.jms.RegistrationDetails;
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
	
	/*
	 * (non-Javadoc)
	 * @see org.metabuild.grobot.client.mq.RegistrationRequestProducer#sendRegistrationRequest()
	 */
	@Override
	public void sendRegistrationRequest() throws JMSException {
		getJmsTemplate().send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage(new RegistrationDetails("hostname", "address"));
				message.setStringProperty("client-uuid", UUID.randomUUID().toString());
				LOGGER.info("Sending registration request to message queue");
				return message;
			}
		});
		
	}
}
