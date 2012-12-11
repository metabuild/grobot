package org.metabuild.grobot.client.registration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.metabuild.grobot.common.jms.RegistrationData;
import org.metabuild.grobot.common.jms.RegistrationDataConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

/**
 * Listens for a registration response and starts the status topic container if registration is successful
 * 
 * @author jburbridge
 * @since 11/19/2012
 */
@Component
public class RegistrationResponseListener implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationResponseListener.class);
	
	private final RegistrationDataConverter messageConverter;
	private final DefaultMessageListenerContainer statusTopicJmsContainer;
	
	public RegistrationResponseListener(DefaultMessageListenerContainer container) {
		LOGGER.info("Initializing {}...", getClass().getName());
		this.statusTopicJmsContainer = container;
		this.messageConverter = new RegistrationDataConverter();
	}
	
	@Override
	public void onMessage(Message message) {
		LOGGER.info("Got registration response: {}", message);
		try {
			RegistrationData registrationData = (RegistrationData) messageConverter.fromMessage(message);
			LOGGER.info("Registration data: {}", registrationData);
		} catch (MessageConversionException e) {
			LOGGER.error("Could not convert registration data", e);
		} catch (JMSException e) {
			LOGGER.error("Could not receive registration data", e);
		}
		statusTopicJmsContainer.start();
	}
}
