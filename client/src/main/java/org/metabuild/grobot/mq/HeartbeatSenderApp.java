package org.metabuild.grobot.mq;

import javax.jms.JMSException;

import org.metabuild.grobot.config.HeartbeatProducerConfig;
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
		ApplicationContext context = new AnnotationConfigApplicationContext(HeartbeatProducerConfig.class); 
		HeartbeatProducer producer = (HeartbeatProducer) context.getBean(HeartbeatProducer.class);
		int x = 0;
		while (x++ < 10) {
			producer.sendHeartbeat();
			Thread.sleep(1000);
		}
	}
}
