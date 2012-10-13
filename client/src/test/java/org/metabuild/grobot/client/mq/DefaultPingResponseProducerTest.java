/**
 * 
 */
package org.metabuild.grobot.client.mq;

import static org.mockito.Mockito.*;

import javax.jms.JMSException;

import org.junit.Test;
import org.metabuild.net.HostnameResolver;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author jburbridge
 *
 */
public class DefaultPingResponseProducerTest {

	/**
	 * Test method for {@link org.metabuild.grobot.client.mq.PingResponseProducerImpl#sendPingResponse()}.
	 * @throws JMSException 
	 */
	@Test
	public void testSendPing() throws JMSException {
		PingResponseProducer producer = new PingResponseProducerImpl(getMockHostnameResolver("fooooey"));
		producer.setJmsTemplate(getMockJmsTemplate());
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
