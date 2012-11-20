package org.metabuild.grobot.client.status;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.metabuild.grobot.jms.StatusMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
@Component
public class StatusRequestListener implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusRequestListener.class);

	@Autowired
	private StatusResponseProducer responseProducer;
	
	public StatusRequestListener() {
		LOGGER.info("Initializing {}...", getClass().getName());
	}
	
	@Override
	public void onMessage(Message message) {
		try {
			String hostname = message.getStringProperty("hostname");
			String messageType = message.getStringProperty(StatusMessageType.class.getSimpleName());
			LOGGER.info("Received status request \"{}\" from {}", messageType, hostname);
			responseProducer.sendStatusResponse();
		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public void setStatusResponseProducer(StatusResponseProducer responseProducer) {
		this.responseProducer = responseProducer;
	}
}
