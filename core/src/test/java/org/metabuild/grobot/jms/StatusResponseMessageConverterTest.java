/**
 * 
 */
package org.metabuild.grobot.jms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.junit.Test;
import org.springframework.jms.support.converter.MessageConversionException;

/**
 * @author jburbridge
 * @since 10/14/2012
 */
public class StatusResponseMessageConverterTest {

	/**
	 * Test method for {@link org.metabuild.grobot.jms.StatusResponseMessageConverter#toMessage(java.lang.Object, javax.jms.Session)}.
	 * @throws JMSException 
	 * @throws MessageConversionException 
	 */
	@Test
	public void testToMessage() throws MessageConversionException, JMSException {
		
		StatusResponseMessageConverter messageConverter = new StatusResponseMessageConverter();
		StatusResponse statusResponse = new StatusResponse();
		Message message = messageConverter.toMessage(statusResponse, getMockSession());
		// TODO: figure out how to improve this test
		assertNotNull(message);
		
	} 

	/**
	 * Test method for {@link org.metabuild.grobot.jms.StatusResponseMessageConverter#fromMessage(javax.jms.Message)}.
	 * @throws JMSException 
	 * @throws MessageConversionException 
	 */
	@Test
	public void testFromMessage() throws MessageConversionException, JMSException {
		
		StatusResponseMessageConverter messageConverter = new StatusResponseMessageConverter();
		Object statusResponse = messageConverter.fromMessage(getMockMessage());
		assertEquals(StatusResponse.class, statusResponse.getClass());
		assertNotNull(statusResponse);
	}

	private Message getMockMessage() throws JMSException {
		ObjectMessage objectMessage = mock(ObjectMessage.class);
		when(objectMessage.getObject()).thenReturn(new StatusResponse());
		return objectMessage;
	}

	private Session getMockSession() throws JMSException {
		Session session = mock(Session.class);
		ObjectMessage objectMessage = mock(ObjectMessage.class);
		when(session.createObjectMessage(any(StatusResponse.class))).thenReturn(objectMessage);
		return session;
	}
}
