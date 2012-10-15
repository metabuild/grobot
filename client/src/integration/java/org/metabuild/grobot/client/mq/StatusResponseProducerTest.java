package org.metabuild.grobot.client.mq;

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
public class StatusResponseProducerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusResponseProducerTest.class);
	
	/**
	 * Test method for {@link org.metabuild.grobot.client.mq.StatusResponseProducerImpl#sendStatusResponse()}.
	 * @throws JMSException 
	 */
	@Test
	public void testSendStatusResponse() throws JMSException {
		LOGGER.info("Sending status response to queue");
		ApplicationContext context = new AnnotationConfigApplicationContext(ClientJmsConfig.class); 
		StatusResponseProducerImpl producer = context.getBean(StatusResponseProducerImpl.class);
		int i = 0;
		while (i++ < 10) {
			producer.sendStatusResponseAs("testhost" + i);
		}
	}
}
