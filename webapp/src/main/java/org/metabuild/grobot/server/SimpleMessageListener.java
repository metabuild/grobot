/**
 * 
 */
package org.metabuild.grobot.server;

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
public class SimpleMessageListener implements MessageListener {

	private static Logger LOGGER = LoggerFactory.getLogger(SimpleMessageListener.class);
	
	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		
		TextMessage textMessage = (TextMessage) message;
		
		try {
			LOGGER.info("Message received: " + textMessage.getText());
		} catch (JMSException ex) {
			LOGGER.error("JMS Error", ex);
			
		}
	}
}
