package org.metabuild.grobot.client.mq;

import javax.jms.JMSException;

import org.metabuild.grobot.client.config.ClientJmsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Test app to send messages to the mq
 * @author jburbridge
 */
public class HeartbeatSenderApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatSenderApp.class);
	
	public static void main(String[] args) throws JMSException, InterruptedException {
		LOGGER.info("Sending heartbeat to queue");
		ApplicationContext context = new AnnotationConfigApplicationContext(ClientJmsConfig.class); 
		HeartbeatProducerImpl producer = (HeartbeatProducerImpl) context.getBean(HeartbeatProducerImpl.class);
		int x = 0;
		while (x++ < 10) {
			producer.sendHeartbeat();
			Thread.sleep(1000);
		}
	}
}
