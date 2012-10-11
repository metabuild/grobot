package org.metabuild.grobot.mq;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jburbridge
 *
 */
public class HeartbeatSenderApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatSenderApp.class);
	
	public static void main(String[] args) throws JMSException {
		LOGGER.info("Sending heartbeat to queue");
		ApplicationContext context = new AnnotationConfigApplicationContext(HeartbeatConfig.class); 
		HeartbeatProducer producer = (HeartbeatProducer) context.getBean(HeartbeatProducer.class);
		producer.sendHeartbeat();
	}
}
