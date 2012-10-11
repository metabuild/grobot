package org.metabuild.grobot.server.mq;

import javax.jms.JMSException;

import org.metabuild.grobot.server.config.HeartbeatConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HeartbeatReceiverApp {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatReceiverApp.class);
	
	public static void main(String[] args) throws JMSException {
		ApplicationContext appContext = new AnnotationConfigApplicationContext(HeartbeatConsumerConfig.class); 
		LOGGER.info("Listening to {}", appContext.getBean("grobotHostsQueue"));
	}
}
