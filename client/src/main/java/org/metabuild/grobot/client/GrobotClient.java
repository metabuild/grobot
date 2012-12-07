package org.metabuild.grobot.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;

import org.metabuild.grobot.client.key.KeyManager;
import org.metabuild.grobot.client.registration.RegistrationRequestProducer;
import org.metabuild.grobot.common.jms.RegistrationData;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
public class GrobotClient implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(GrobotClient.class);
	private final ApplicationContext context;
	private final String hostname;
	private final String address;
	
	/**
	 * @param name
	 * @param context 
	 */
	public GrobotClient(ApplicationContext context, String name, String address) {
		this.hostname = name;
		this.context = context;
		this.address = address;
	}

	/**
	 * Start the RegistrationResponseListener and send a RegistrationRequest to the Grobot master
	 */
	@Override
	public void run() {
		try {
			LOGGER.info("Starting {}'s registrationResponseListenerContainer...", hostname);
			final DefaultMessageListenerContainer registrationResponseListenerContainer = (DefaultMessageListenerContainer) context.getBean("registrationResponseListenerContainer");
			registrationResponseListenerContainer.start();
			LOGGER.info("Now listening for registration response on {}...", registrationResponseListenerContainer.getDestination());
			
			LOGGER.info("Loading key for {}...", hostname);
			final KeyManager keyManager = (KeyManager) context.getBean("keyManager");
			
			LOGGER.info("Attempting to register {} with Grobot master...", hostname);
			final RegistrationRequestProducer registrationRequestProducer = (RegistrationRequestProducer) context.getBean("registrationRequestProducer");
			registrationRequestProducer.sendRegistrationRequest(new RegistrationData(keyManager.loadKey(), hostname, address));
			
		} catch (JMSException e) {
			LOGGER.error("Grobot could not register with the server - is the Grobot server running?", e);
		}
	}
}
