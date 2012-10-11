package org.metabuild.grobot.mq;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

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
		final String hostname = getHostname();
		final MessageCreator messageCreator = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(ping);
				textMessage.setStringProperty("hostname", hostname);
				LOGGER.info("Sending heartbeat ping to grobot master for {}", hostname);
				return textMessage;
			}
			
		};
		jmsTemplate.send(messageCreator);
	}

	private String getHostname() {
		String hostname = null;
		try {
		    InetAddress addr = InetAddress.getLocalHost();
		    // byte[] ipAddr = addr.getAddress();
		    hostname = addr.getHostName();
		} catch (UnknownHostException e) {
			LOGGER.error("Could not set hostname", e);
		}
		return hostname;
	}
}
