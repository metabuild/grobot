package org.metabuild.grobot.client;

import javax.jms.JMSException;

import org.metabuild.grobot.client.config.ClientJmsConfig;
import org.metabuild.grobot.client.mq.StatusRequestListener;
import org.metabuild.grobot.client.mq.StatusResponseProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
public class GrobotClient implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(GrobotClient.class);
	private final String clientName;
	private final ApplicationContext context;
	
	public GrobotClient(String name) {
		clientName = name;
		context = new AnnotationConfigApplicationContext(ClientJmsConfig.class);
	}

	@Override
	public void run() {
		try {
			start(); 
		} catch (JMSException e) {
			LOGGER.error("Failed to start Grobot due to a JMSException", e);
		} 
		try {
			register();
		} catch (Exception e) {
			LOGGER.error("Grobot could not register with the server - is the Grobot server running?", e);
		}
	}
	
	/**
	 * Initializes the status request listener
	 * 
	 * @throws JMSException
	 */
	public void start() throws JMSException {
		LOGGER.info("Starting GrobotClient {}...", clientName);
		// register the listener
		StatusRequestListener listener = (StatusRequestListener) context.getBean("statusRequestListner");
		LOGGER.info("Started listener {} for {}...", listener.getClass().getName(), clientName);
	}
	
	/**
	 * Sends a status response to the status queue registering the client
	 * 
	 * @throws JMSException
	 */
	public void register() throws JMSException {
		LOGGER.info("Registering GrobotClient {}...", clientName);
		// announce to the server that we're alive
		StatusResponseProducer responseProducer = (StatusResponseProducer) context.getBean("statusResponseProducer");
		responseProducer.sendStatusResponse();
	}
}
