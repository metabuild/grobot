package org.metabuild.grobot.server.mq;

import javax.jms.JMSException;

import org.springframework.jms.core.JmsTemplate;

/**
 * Classes implementing this interface send status request from the Grobot server by 
 * producing a StatusRequest message and putting into the message topic.
 * 
 * @author jburbridge
 * @since 10/11/2012
 */
public interface StatusRequestProducer {

	/**
	 * Sends the status request to the message topic
	 * @throws JMSException
	 */
	public void sendStatusRequest() throws JMSException;

	/**
	 * Allows for the jmsTemplate to be injected for unit testing
	 * @param jmsTemplate
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate);

}
