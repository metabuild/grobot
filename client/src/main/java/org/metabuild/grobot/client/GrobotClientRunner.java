package org.metabuild.grobot.client;

import javax.jms.JMSException;

import org.metabuild.grobot.client.config.ClientJmsConfig;
import org.metabuild.grobot.client.mq.StatusRequestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.UncategorizedJmsException;

/**
 * @author jburbridge
 *
 */
public class GrobotClientRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(GrobotClientRunner.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final GrobotClient client = new GrobotClient(System.getProperty("hostname"));
		
		try {
			client.start(); 
		} catch (JMSException e) {
			LOGGER.error("Failed to start Grobot due to a JMSException", e);
		} 
		
		try {
			if (client != null)
				client.register();
		} catch (Exception e) {
			LOGGER.error("Grobot could not register with the server - is the Grobot server running?", e);
		}
	}
}
