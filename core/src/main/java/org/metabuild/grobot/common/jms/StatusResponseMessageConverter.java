package org.metabuild.grobot.common.jms;

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
public class StatusResponseMessageConverter implements MessageConverter {

	/* (non-Javadoc)
	 * @see org.springframework.jms.support.converter.MessageConverter#toMessage(java.lang.Object, javax.jms.Session)
	 */
	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		StatusResponse statusResponse = (StatusResponse) object;
		ObjectMessage message = session.createObjectMessage(statusResponse);
		return message;
	}

	/* (non-Javadoc)
	 * @see org.springframework.jms.support.converter.MessageConverter#fromMessage(javax.jms.Message)
	 */
	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		ObjectMessage objectMessage = (ObjectMessage) message;
		StatusResponse statusResponse = (StatusResponse) objectMessage.getObject();
		return statusResponse;
	}
}
