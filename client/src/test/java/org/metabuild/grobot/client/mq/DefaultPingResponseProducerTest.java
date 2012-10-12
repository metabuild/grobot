/**
 * 
 */
package org.metabuild.grobot.client.mq;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.jms.JMSException;

import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author jburbridge
 *
 */
public class DefaultPingResponseProducerTest {

	/**
	 * Test method for {@link org.metabuild.grobot.client.mq.DefaultPingResponseProducer#sendPingResponse()}.
	 * @throws JMSException 
	 */
	@Test
	public void testSendPing() throws JMSException {
		PingResponseProducer producer = new DefaultPingResponseProducer(getMockHostnameResolver("fooooey"), getMockJmsTemplate());
		producer.sendPingResponse();
	}
	
	private JmsTemplate getMockJmsTemplate() {
		JmsTemplate jmsTemplate = mock(JmsTemplate.class);
		return jmsTemplate;
	}
	private HostnameResolver getMockHostnameResolver(String hostname) {
		HostnameResolver resolver = mock(HostnameResolver.class);
		when(resolver.getHostname()).thenReturn(hostname);
		return resolver;
	}
}
