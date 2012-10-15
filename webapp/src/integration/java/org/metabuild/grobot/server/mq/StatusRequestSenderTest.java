/**
 * 
 */
package org.metabuild.grobot.server.mq;

import static org.junit.Assert.*;

import javax.jms.JMSException;

import org.junit.Ignore;
import org.junit.Test;
import org.metabuild.grobot.server.config.ServerJmsConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jburbridge
 *
 */
public class StatusRequestSenderTest {

	@Ignore("This intergration test requires that an mq broker be running")
	@Test
	public void test() throws JMSException, InterruptedException {
		ApplicationContext context = new AnnotationConfigApplicationContext(ServerJmsConfig.class); 
		StatusRequestProducer producer = context.getBean(StatusRequestProducer.class);
		int x = 0;
		while (x++ < 10) {
			producer.sendStatusRequest();
			Thread.sleep(5000);
		}
	}
}
