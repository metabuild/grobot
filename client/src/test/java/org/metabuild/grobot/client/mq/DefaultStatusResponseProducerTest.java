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
 * @since 10/13/2012
 */
public class DefaultStatusResponseProducerTest {

	/**
	 * Test method for {@link org.metabuild.grobot.client.mq.StatusResponseProducerImpl#sendStatusResponse()}.
	 * @throws JMSException 
	 */
	@Test
	public void testSendStatus() throws JMSException {
		StatusResponseProducer producer = new StatusResponseProducerImpl(getMockHostnameResolver("fooooey"));
		producer.setJmsTemplate(getMockJmsTemplate());
		producer.sendStatusResponse();
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
