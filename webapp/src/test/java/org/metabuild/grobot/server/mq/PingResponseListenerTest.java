/**
 * 
 */
package org.metabuild.grobot.server.mq;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Properties;

import javax.jms.JMSException;

import org.junit.Test;
import org.metabuild.grobot.domain.TargetCache;
import org.metabuild.grobot.mq.PingResponse;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

/**
 * This was a tricky test to figure out because there doesn't seem to be much documentation supporting 
 * how to unit test a listener that extends Spring's JmsGatewaySupport. Turns out that all you have to 
 * do is inject a mock JmsTemplate that returns the actual object as a result of receiveAndConvert().
 * 
 * @author jburbridge
 * @since 10/13/2012
 */
public class PingResponseListenerTest {

	@Test
	public void testReceivePingResponse() throws JMSException {
		
		PingResponseListener listener = new PingResponseListener(new TargetCache());
		
		listener.setJmsTemplate(getMockJmsTemplate("test1"));
		listener.receivePingResponse();
		assertEquals(1,listener.getTargetCache().size());
		
		listener.setJmsTemplate(getMockJmsTemplate("test2"));
		listener.receivePingResponse();
		assertEquals(2,listener.getTargetCache().size());
		
		listener.setJmsTemplate(getMockJmsTemplate("test2"));
		listener.receivePingResponse();
		assertEquals(2,listener.getTargetCache().size());
	}

	private JmsTemplate getMockJmsTemplate(String hostname) throws JmsException, JMSException {
		JmsTemplate jmsTemplate = mock(JmsTemplate.class);
		when(jmsTemplate.receiveSelectedAndConvert(anyString())).thenReturn(new PingResponse(hostname, new Properties(), new Properties()));		
		return jmsTemplate;
	}
}
