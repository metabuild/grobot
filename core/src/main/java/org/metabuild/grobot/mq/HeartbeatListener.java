package org.metabuild.grobot.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jburbridge
 *
 */
public class HeartbeatListener implements MessageListener {

	 private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatListener.class);
	 
	@Override
	public void onMessage(Message message) {
		try {
			LOGGER.info("Received heartbeat: {}", ((TextMessage) message).getText());
		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
	}

}
