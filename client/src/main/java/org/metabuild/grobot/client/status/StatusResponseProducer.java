package org.metabuild.grobot.client.status;

import javax.jms.JMSException;

import org.springframework.jms.core.JmsTemplate;

/**
 * Classes implementing this interface respond to a status request from the Grobot server by 
 * producing a StatusResponse message and putting into the status queue.
 * 
 * @author jburbridge
 * @since 10/11/2012
 */
public interface StatusResponseProducer {

	/**
	 * Sends the status response to the message topic
	 * @throws JMSException
	 */
	public void sendStatusResponse() throws JMSException;

	/**
	 * Allows for the jmsTemplate to be injected for unit testing
	 * @param jmsTemplate
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate);
	
}
