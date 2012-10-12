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
 * @since 10/11/2012
 */
public class PingResponseSenderApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingResponseSenderApp.class);
	
	public static void main(String[] args) throws JMSException, InterruptedException {
		LOGGER.info("Sending pings to queue");
		ApplicationContext context = new AnnotationConfigApplicationContext(ClientJmsConfig.class); 
		DefaultPingResponseProducer producer = (DefaultPingResponseProducer) context.getBean(DefaultPingResponseProducer.class);
		int x = 0;
		while (x++ < 10) {
			producer.sendPingResponse();
			Thread.sleep(1000);
		}
	}
}
