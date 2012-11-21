package org.metabuild.grobot.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;

import org.metabuild.grobot.client.registration.RegistrationRequestProducer;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
public class GrobotClient implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(GrobotClient.class);
	private final ApplicationContext context;
	private final String clientName;
	
	/**
	 * @param name
	 * @param context 
	 */
	public GrobotClient(String name, ApplicationContext context) {
		this.clientName = name;
		this.context = context;
	}

	/**
	 * Start the RegistrationResponseListener and send a RegistrationRequest to the Grobot master
	 */
	@Override
	public void run() {
		try {
			LOGGER.info("Starting {}'s RegistrationResponseListener...", clientName);
			DefaultMessageListenerContainer registrationResponseTopicJmsContainer = (DefaultMessageListenerContainer) context.getBean("registrationResponseTopicJmsContainer");
			registrationResponseTopicJmsContainer.start();
			
			LOGGER.info("Attempting to register {} with Grobot master...", clientName);
			RegistrationRequestProducer registrationRequestProducer = (RegistrationRequestProducer) context.getBean("registrationRequestProducer");
			registrationRequestProducer.sendRegistrationRequest(clientName);
		} catch (JMSException e) {
			LOGGER.error("Grobot could not register with the server - is the Grobot server running?", e);
		}
	}
}
