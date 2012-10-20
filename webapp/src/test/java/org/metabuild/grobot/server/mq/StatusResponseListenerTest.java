package org.metabuild.grobot.server.mq;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.junit.Test;
import org.metabuild.grobot.domain.TargetCacheImpl;
import org.metabuild.grobot.mq.StatusResponse;

/**
 * @author jburbridge
 * @since 10/13/2012
 */
public class StatusResponseListenerTest {

	@Test
	public void testReceiveStatusResponse() throws JMSException {
		
		StatusResponseListener listener = new StatusResponseListener(new TargetCacheImpl());
		
		listener.onMessage(getMockObjectMessage("hostname1"));
		assertEquals(1,listener.getTargetCache().size());
		
		listener.onMessage(getMockObjectMessage("hostname2"));
		assertEquals(2,listener.getTargetCache().size());
		
		// duplicate message
		listener.onMessage(getMockObjectMessage("hostname2"));
		assertEquals(2,listener.getTargetCache().size());
		
		listener.onMessage(getMockObjectMessage("hostname3"));
		assertEquals(3,listener.getTargetCache().size());
	}

	private ObjectMessage getMockObjectMessage(String hostname) throws JMSException {
		ObjectMessage objectMessage = mock(ObjectMessage.class);
		when(objectMessage.getObject()).thenReturn(new StatusResponse(hostname, null, null));
		return objectMessage;
	}
}
