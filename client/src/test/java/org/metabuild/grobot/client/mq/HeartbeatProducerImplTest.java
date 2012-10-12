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
public class HeartbeatProducerImplTest {

	/**
	 * Test method for {@link org.metabuild.grobot.client.mq.HeartbeatProducerImpl#sendHeartbeat()}.
	 * @throws JMSException 
	 */
	@Test
	public void testSendHeartbeat() throws JMSException {
		HeartbeatProducerImpl producer = new HeartbeatProducerImpl(getMockHostnameResolver("fooooey"), getMockJmsTemplate());
		producer.sendHeartbeat();
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
