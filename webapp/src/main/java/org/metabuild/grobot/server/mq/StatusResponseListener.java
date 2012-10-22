package org.metabuild.grobot.server.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.metabuild.grobot.domain.TargetHostCache;
import org.metabuild.grobot.domain.TargetHostCacheImpl;
import org.metabuild.grobot.domain.TargetHost;
import org.metabuild.grobot.mq.StatusResponse;
import org.metabuild.grobot.mq.StatusResponseMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jms.support.converter.MessageConversionException;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
@Component
public class StatusResponseListener implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusResponseListener.class);
	
	@Autowired
	private TargetHostCache targetHostCache;
	
	private final StatusResponseMessageConverter messageConverter;
	
	public StatusResponseListener() {
		this.messageConverter = new StatusResponseMessageConverter();
		LOGGER.info("Initializing {}...", StatusResponseListener.class.getName());
	}
	
	/**
	 * Constructor with DI for unit testing
	 * @param targetHostCache
	 */
	public StatusResponseListener(TargetHostCache targetHostCache) {
		this();
		this.targetHostCache = targetHostCache;
	}
	
	/**
	 * @return the targetCache
	 */
	protected TargetHostCache getTargetCache() {
		return targetHostCache;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		try {
			StatusResponse statusResponse = (StatusResponse) messageConverter.fromMessage(message);
			String hostname = statusResponse.getHostname();
			LOGGER.info("Received ping response \"{}\" from {}", statusResponse, hostname);
			if (hostname != null && targetHostCache.get(hostname) == null) {
				LOGGER.info("Adding {} to targetCache", hostname);
				targetHostCache.put(hostname, new TargetHost(hostname, hostname, true));
			}
		} catch (MessageConversionException e) {
			LOGGER.error("Could not convert Message to StatusResponse", e);
		} catch (JMSException e) {
			LOGGER.error("Error receiving StatusResponse message", e);
		}
	}
}