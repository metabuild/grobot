package org.metabuild.grobot.client.mq;

import java.net.UnknownHostException;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.stereotype.Component;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
@Component
public class PingRequestListener  extends JmsGatewaySupport  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingRequestListener.class);
	
	public PingRequestListener() {
		LOGGER.info("Initializing {}...", PingRequestListener.class.getName());
	}
	
	public void receivePingRequest() {
		try {
			TextMessage pingRequest = (TextMessage) getJmsTemplate().receiveSelected("ping = 'request'");
			String hostname = pingRequest.getStringProperty("hostname");
			LOGGER.info("Received ping request \"{}\" from {}", pingRequest.getText(), hostname);
			try {
				PingResponseProducerImpl producer = new PingResponseProducerImpl();
				producer.sendPingResponse();
			} catch (UnknownHostException e) {
				LOGGER.error("Could not resolve local hostname required for sending a ping respone", e);
			}
		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
