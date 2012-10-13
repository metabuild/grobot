/**
 * 
 */
package org.metabuild.grobot.server.mq;

import static org.junit.Assert.*;

import javax.jms.JMSException;

import org.junit.Test;
import org.metabuild.grobot.server.config.ServerJmsConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jburbridge
 *
 */
public class PingRequestSenderTest {

	@Test
	public void test() throws JMSException {
		ApplicationContext context = new AnnotationConfigApplicationContext(ServerJmsConfig.class); 
		PingRequestProducer producer = context.getBean(PingRequestProducer.class);
//		int x = 0;
//		while (x++ < 10) {
			producer.sendPingRequest();
//			Thread.sleep(1000);
//		}
	}
}
