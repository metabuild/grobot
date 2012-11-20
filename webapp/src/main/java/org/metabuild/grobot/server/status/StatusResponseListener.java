package org.metabuild.grobot.server.status;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.joda.time.DateTime;
import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.common.jms.StatusResponse;
import org.metabuild.grobot.common.jms.StatusResponseMessageConverter;
import org.metabuild.grobot.server.service.TargetHostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;


/**
 * @author jburbridge
 * @since 10/12/2012
 */
@Component
public class StatusResponseListener implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusResponseListener.class);
	
	private final TargetHostService targetHostService;
	private final StatusResponseMessageConverter messageConverter;

	
	/**
	 * Constructor with DI for unit testing
	 * @param targetHostService
	 * @param targetHostRegistrationManager
	 */
	public StatusResponseListener(TargetHostService targetHostService) {
		LOGGER.info("Initializing {}...", this.getClass().getName());
		this.messageConverter = new StatusResponseMessageConverter();
		this.targetHostService = targetHostService;
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		try {
			final StatusResponse statusResponse = (StatusResponse) messageConverter.fromMessage(message);
			final String hostname = statusResponse.getHostname();
			LOGGER.info("Received \"{}\" from {}", statusResponse, hostname);
			if (hostname != null) {
				TargetHost targetHost = targetHostService.findByName(hostname);
				if (targetHost != null) {
					targetHostService.findByName(hostname).setLastUpdatedStatus(new DateTime(statusResponse.getTimeStamp()));
				} else {
					LOGGER.warn("Unregistered target host {} is listening on status topic!", hostname);
				}
			}
		} catch (MessageConversionException e) {
			LOGGER.error("Could not convert Message to StatusResponse", e);
		} catch (JMSException e) {
			LOGGER.error("Error receiving StatusResponse message", e);
		}
	}
	
	/**
	 * @return the targetHostService
	 */
	protected TargetHostService getTargetHostService() {
		return targetHostService;
	}
}
