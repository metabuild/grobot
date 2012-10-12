package org.metabuild.grobot.client.mq;

import javax.jms.JMSException;

import org.springframework.jms.core.JmsTemplate;

/**
 * 
 * @author jburbridge
 * 10/11/2012
 */
public interface HeartbeatProducer {

	public void sendHeartbeat() throws JMSException;

	public void setJmsTemplate(JmsTemplate jmsTemplate);
	
}
