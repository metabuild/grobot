/**
 * 
 */
package org.metabuild.grobot.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

/**
 * @author jburbridge
 * @since 10/11/2012
 */
public class PingResponseMessageConverter implements MessageConverter {

	/* (non-Javadoc)
	 * @see org.springframework.jms.support.converter.MessageConverter#toMessage(java.lang.Object, javax.jms.Session)
	 */
	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		PingResponse pingResponse = (PingResponse) object;
		ObjectMessage message = session.createObjectMessage(pingResponse);
		return message;
	}

	/* (non-Javadoc)
	 * @see org.springframework.jms.support.converter.MessageConverter#fromMessage(javax.jms.Message)
	 */
	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		ObjectMessage objectMessage = (ObjectMessage) message;
		PingResponse pingResponse = (PingResponse) objectMessage.getObject();
		return pingResponse;
	}
}
