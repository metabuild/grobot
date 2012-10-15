package org.metabuild.grobot.client;

import org.metabuild.grobot.client.config.ClientJmsConfig;
import org.metabuild.grobot.client.mq.StatusRequestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
		LOGGER.info("Starting Grobot Client");
		ApplicationContext context = new AnnotationConfigApplicationContext(ClientJmsConfig.class);
		StatusRequestListener listener = (StatusRequestListener) context.getBean("statusRequestListner");
		LOGGER.info("Started listener {}...", listener.getClass().getName());
	}

}
