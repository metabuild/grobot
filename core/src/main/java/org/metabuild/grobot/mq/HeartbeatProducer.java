package org.metabuild.grobot.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author jburbridge
 *
 */
public class HeartbeatProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatProducer.class);
    
    protected JmsTemplate jmsTemplate; 
    
    public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendHeartbeat() throws JMSException {
		final String ping = "PING";
		final MessageCreator messageCreator = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				LOGGER.info("Sending heartbeat ping to grobot master");
				return session.createTextMessage(ping);
			}
			
		};
		jmsTemplate.send(messageCreator);
	}

}
