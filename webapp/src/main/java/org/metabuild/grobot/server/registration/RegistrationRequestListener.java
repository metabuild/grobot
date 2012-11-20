package org.metabuild.grobot.server.registration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Listens for incoming registration requests
 * 
 * @author jburbridge
 * @since 11/17/2012
 */
@Component
public class RegistrationRequestListener  implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationRequestListener.class);

	private final RegistrationService registrationService;

	public RegistrationRequestListener(RegistrationService registrationService) {
		LOGGER.info("Initializing {}...", this.getClass().getName());
		this.registrationService = registrationService;
	}

	@Override
	public void onMessage(Message message) {
		String hostname;
		String clientUuid;
		try {
			hostname = message.getStringProperty("hostname");
			clientUuid = message.getStringProperty("client-uuid");
			LOGGER.info("Received registration request from {} with uuid {}", hostname, clientUuid);
			registrationService.handleRegistrationRequest(message);
		} catch (JMSException e) {
			LOGGER.error("Error receiving registration request", e);
		}
	}
}
