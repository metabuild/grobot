package org.metabuild.grobot.client.mq;

import javax.jms.JMSException;

import org.metabuild.grobot.client.config.ClientJmsConfig;
import org.metabuild.grobot.client.status.StatusResponseProducerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Test app to send messages to the mq
 * @author jburbridge
 * @since 10/11/2012
 */
public class StatusResponseSenderApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusResponseSenderApp.class);
	
	public static void main(String[] args) throws JMSException, InterruptedException {
		LOGGER.info("Sending status responses to queue");
		ApplicationContext context = new AnnotationConfigApplicationContext(ClientJmsConfig.class); 
		StatusResponseProducerImpl producer = context.getBean(StatusResponseProducerImpl.class);
		int x = 0;
		while (x++ < 10) {
			producer.sendStatusResponse();
			Thread.sleep(1000);
		}
	}
}
