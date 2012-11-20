package org.metabuild.grobot.client.registration;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author jburbridge
 * @since 11/19/2012
 */
@Component
public class RegistrationResponseListener implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationResponseListener.class);
	
	public RegistrationResponseListener() {
		LOGGER.info("Initializing {}...", getClass().getName());
	}
	
	@Override
	public void onMessage(Message message) {
		LOGGER.info("Got registration response: {}", message);
	}
}
