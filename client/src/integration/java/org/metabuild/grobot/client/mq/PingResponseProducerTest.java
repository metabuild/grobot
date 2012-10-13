package org.metabuild.grobot.client.mq;
/**
 * 
 */


import javax.jms.JMSException;

import org.junit.Test;
import org.metabuild.grobot.client.config.ClientJmsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jburbridge
 *
 */
public class PingResponseProducerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingResponseProducerTest.class);
	
	/**
	 * Test method for {@link org.metabuild.grobot.client.mq.PingResponseProducerImpl#sendPingResponse()}.
	 * @throws JMSException 
	 */
	@Test
	public void testSendPing() throws JMSException {
		LOGGER.info("Sending pings to queue");
		ApplicationContext context = new AnnotationConfigApplicationContext(ClientJmsConfig.class); 
		PingResponseProducerImpl producer = context.getBean(PingResponseProducerImpl.class);
		producer.sendPingResponse();
	}
}
