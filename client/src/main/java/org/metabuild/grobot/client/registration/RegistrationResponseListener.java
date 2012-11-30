package org.metabuild.grobot.client.registration;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * Listens for the registration response and starts the status topic container if it receives one
 * 
 * @author jburbridge
 * @since 11/19/2012
 */
@Component
public class RegistrationResponseListener implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationResponseListener.class);
	
	private final DefaultMessageListenerContainer statusTopicJmsContainer;
	
	public RegistrationResponseListener(DefaultMessageListenerContainer container) {
		LOGGER.info("Initializing {}...", getClass().getName());
		this.statusTopicJmsContainer = container;
	}
	
	@Override
	public void onMessage(Message message) {
		LOGGER.info("Got registration response: {}", message);
		statusTopicJmsContainer.start();
	}
}
