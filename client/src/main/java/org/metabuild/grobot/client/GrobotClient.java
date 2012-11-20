package org.metabuild.grobot.client;

import javax.jms.JMSException;

import org.metabuild.grobot.client.registration.RegistrationRequestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

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

	@Override
	public void run() {
		try {
			register();
		} catch (Exception e) {
			LOGGER.error("Grobot could not register with the server - is the Grobot server running?", e);
		}
	}
	
	/**
	 * Send a registration request to the grobot server
	 * 
	 * @throws JMSException
	 */
	public void register() throws JMSException {
		LOGGER.info("Starting GrobotClient {}...", clientName);
		RegistrationRequestProducer registrationRequestProducer = (RegistrationRequestProducer) context.getBean("registrationRequestProducer");
		registrationRequestProducer.sendRegistrationRequest();
	}
	
	public void startStatusRequestListener() {
		// register the listener
//		StatusRequestListener listener = (StatusRequestListener) context.getBean("statusRequestListner");
//		LOGGER.info("Started listener {} for {}...", listener.getClass().getName(), clientName);
	}
	
	/**
	 * Sends a status response to the status queue registering the client
	 * 
	 * @throws JMSException
	 */
//	public void register() throws JMSException {
//		LOGGER.info("Registering GrobotClient {}...", clientName);
//		// announce to the server that we're alive and need a handshake
//		StatusResponseProducer responseProducer = (StatusResponseProducer) context.getBean("statusResponseProducer");
//		responseProducer.sendStatusResponse();
//	}
}
