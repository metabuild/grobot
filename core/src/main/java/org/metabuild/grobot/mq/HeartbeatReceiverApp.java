package org.metabuild.grobot.mq;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HeartbeatReceiverApp {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatReceiverApp.class);
	
	public static void main(String[] args) throws JMSException {
		LOGGER.info("Listening to queue");
		new AnnotationConfigApplicationContext(HeartbeatConfig.class); 
	}
}
