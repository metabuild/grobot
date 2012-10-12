package org.metabuild.grobot.client.mq;

import javax.jms.JMSException;

import org.springframework.jms.core.JmsTemplate;

/**
 * Classes implementing this interface respond to a ping request from the Grobot server by 
 * producing a PingResponse message and putting into the status queue.
 * 
 * @author jburbridge
 * @since 10/11/2012
 */
public interface PingResponseProducer {

	public void sendPingResponse() throws JMSException;

	public void setJmsTemplate(JmsTemplate jmsTemplate);
	
}
