package org.metabuild.grobot.client.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
@Component
public class StatusRequestListener  implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusRequestListener.class);

	@Autowired
	private StatusResponseProducer responseProducer;
	
	public StatusRequestListener() {
		LOGGER.info("Initializing {}...", StatusRequestListener.class.getName());
	}
	
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage pingRequest = (TextMessage) message;
			String hostname = pingRequest.getStringProperty("hostname");
			LOGGER.info("Received ping request \"{}\" from {}", pingRequest.getText(), hostname);
			responseProducer.sendStatusResponse();
		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public void setStatusResponseProducer(StatusResponseProducer responseProducer) {
		this.responseProducer = responseProducer;
	}
}
