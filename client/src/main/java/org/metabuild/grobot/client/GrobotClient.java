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
 *
 */
public class GrobotClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(GrobotClient.class);
	private final String NAME;
	private final ApplicationContext context;
	
	public GrobotClient(String name) {
		NAME = name;
		context = new AnnotationConfigApplicationContext(ClientJmsConfig.class);
	}
	
	public void start() throws JMSException {
		LOGGER.info("Starting GrobotClient {}...", NAME);
		// register the listener
		StatusRequestListener listener = (StatusRequestListener) context.getBean("statusRequestListner");
		LOGGER.info("Started listener {} for {}...", listener.getClass().getName(), NAME);
	}
	
	public void register() throws JMSException {
		LOGGER.info("Registering GrobotClient {}...", NAME);
		// announce to the server that we're alive
		StatusResponseProducer responseProducer = (StatusResponseProducer) context.getBean("statusResponseProducer");
		responseProducer.sendStatusResponse();
	}
}
