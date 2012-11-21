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
import org.metabuild.grobot.common.jms.RegistrationData;
import org.metabuild.grobot.common.jms.RegistrationDataConverter;
import org.springframework.jms.support.converter.MessageConversionException;

/**
 * @author jburbridge
 * @since 11/21/2012
 */
public class RegistrationDataConverterTest {

	/**
	 * Test method for {@link org.metabuild.grobot.common.jms.StatusResponseMessageConverter#toMessage(java.lang.Object, javax.jms.Session)}.
	 * @throws JMSException 
	 * @throws MessageConversionException 
	 */
	@Test
	public void testToMessage() throws MessageConversionException, JMSException {
		
		RegistrationDataConverter regData = new RegistrationDataConverter();
		RegistrationData data = new RegistrationData("foo", "bar");
		Message message = regData.toMessage(data, getMockSession());
		// TODO: figure out how to improve this test
		assertNotNull(message);
		
	} 

	/**
	 * Test method for {@link org.metabuild.grobot.common.jms.StatusResponseMessageConverter#fromMessage(javax.jms.Message)}.
	 * @throws JMSException 
	 * @throws MessageConversionException 
	 */
	@Test
	public void testFromMessage() throws MessageConversionException, JMSException {
		
		RegistrationDataConverter regData = new RegistrationDataConverter();
		Object registrationData = regData.fromMessage(getMockMessage());
		assertEquals(RegistrationData.class, registrationData.getClass());
		assertNotNull(registrationData);
	}

	private Message getMockMessage() throws JMSException {
		ObjectMessage objectMessage = mock(ObjectMessage.class);
		when(objectMessage.getObject()).thenReturn(new RegistrationData("foo","bar"));
		return objectMessage;
	}

	private Session getMockSession() throws JMSException {
		Session session = mock(Session.class);
		ObjectMessage objectMessage = mock(ObjectMessage.class);
		when(session.createObjectMessage(any(RegistrationData.class))).thenReturn(objectMessage);
		return session;
	}
}
