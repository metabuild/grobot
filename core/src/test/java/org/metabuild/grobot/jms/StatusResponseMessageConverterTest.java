/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
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
import org.metabuild.grobot.common.jms.StatusResponse;
import org.metabuild.grobot.common.jms.StatusResponseMessageConverter;
import org.springframework.jms.support.converter.MessageConversionException;

/**
 * @author jburbridge
 * @since 10/14/2012
 */
public class StatusResponseMessageConverterTest {

	/**
	 * Test method for {@link org.metabuild.grobot.common.jms.StatusResponseMessageConverter#toMessage(java.lang.Object, javax.jms.Session)}.
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
	 * Test method for {@link org.metabuild.grobot.common.jms.StatusResponseMessageConverter#fromMessage(javax.jms.Message)}.
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
