package org.metabuild.grobot.server.mq;

import javax.jms.JMSException;

import org.springframework.jms.core.JmsTemplate;

/**
 * Classes implementing this interface send ping request from the Grobot server by 
 * producing a PingRequest message and putting into the message topic.
 * 
 * @author jburbridge
 * @since 10/11/2012
 */
public interface PingRequestProducer {

	/**
	 * Sends the ping request to the message topic
	 * @throws JMSException
	 */
	public void sendPingRequest() throws JMSException;

	/**
	 * Allows for the jmsTemplate to be injected for unit testing
	 * @param jmsTemplate
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate);

}
