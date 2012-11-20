package org.metabuild.grobot.server.status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.junit.Test;
import org.metabuild.grobot.jms.StatusResponse;
import org.metabuild.grobot.server.mq.FakeTargetHostServiceImpl;

/**
 * @author jburbridge
 * @since 10/13/2012
 */
public class StatusResponseListenerTest {

	/**
	 * @throws JMSException
	 */
	@Test
	public void testReceiveStatusResponseFromKnownTarget() throws JMSException {

		/*
		 * Doesn't seem like there's much to test other than the indirect effects from receiving 
		 * different status response messages which can be verified by inspecting the TargetHostService
		 * 
		 */
		StatusResponseListener listener = new StatusResponseListener(new FakeTargetHostServiceImpl());
		
		listener.onMessage(getMockObjectMessage("valid1"));
		assertEquals(1,listener.getTargetHostService().findAll().size());
		
		listener.onMessage(getMockObjectMessage("valid2"));
		assertEquals(2,listener.getTargetHostService().findAll().size());
		
		// duplicate message
		listener.onMessage(getMockObjectMessage("valid2"));
		assertEquals(2,listener.getTargetHostService().findAll().size());
		
		// invalid host that hasn't been registered yet
		listener.onMessage(getMockObjectMessage("invalid1"));
		assertEquals(2,listener.getTargetHostService().findAll().size());

	}

	private ObjectMessage getMockObjectMessage(String hostname) throws JMSException {
		ObjectMessage objectMessage = mock(ObjectMessage.class);
		when(objectMessage.getObject()).thenReturn(new StatusResponse(hostname, null, null));
		return objectMessage;
	}
}
