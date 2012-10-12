/**
 * 
 */
package org.metabuild.grobot.server.mq;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.junit.Test;
import org.metabuild.grobot.domain.TargetCache;
import org.metabuild.grobot.domain.TargetHost;


/**
 * @author jburbridge
 *
 */
public class HeartbeatListenerTest {

	@Test
	public void test() throws JMSException {
		HeartbeatListener listener = new HeartbeatListener(new TargetCache());
		listener.onMessage(getMockTextMessage("test1"));
		assertEquals(1,listener.getTargetCache().size());
		listener.onMessage(getMockTextMessage("test2"));
		assertEquals(2,listener.getTargetCache().size());
		listener.onMessage(getMockTextMessage("test2"));
		assertEquals(2,listener.getTargetCache().size());
	}

	private TextMessage getMockTextMessage(String hostname) throws JMSException {
		TextMessage mockTextMessage = mock(TextMessage.class);
		when(mockTextMessage.getStringProperty("hostname")).thenReturn(hostname);
		when(mockTextMessage.getText()).thenReturn("P0ING!");
		return mockTextMessage;
	}
}
