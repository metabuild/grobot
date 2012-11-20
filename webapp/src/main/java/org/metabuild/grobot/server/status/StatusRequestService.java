package org.metabuild.grobot.server.status;

import java.util.concurrent.atomic.AtomicLong;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This service sends periodic status requests to the minions for monitoring purposes. It's a Runnable 
 * that wraps a StatusRequestProducer and keeps track of when the last request was submitted to 
 * the minions so that timeouts can be derived from it. 
 * 
 * @author jburbridge
 * @since 11/17/2012
 */
@Service
public class StatusRequestService implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusRequestService.class);
	private static final AtomicLong lastRunTimestamp = new AtomicLong(System.currentTimeMillis());
	
	/**
	 * The status request producer to use, typically via JMS
	 */
	private final StatusRequestProducer statusRequestProducer;
	
	/**
	 * Default constructor
	 * @param statusRequestProducer
	 */
	public StatusRequestService(StatusRequestProducer statusRequestProducer) {
		this.statusRequestProducer = statusRequestProducer;
	}

	/**
	 * Sends a new status request message to the status update topic and updates the timestamp
	 */
	@Override
	public void run() {
		LOGGER.info("Service run started at {}.", getLastRunTimestamp());
		try {
			statusRequestProducer.sendStatusRequest();
			updateLastRunTimestamp();
		} catch (JMSException e) {
			LOGGER.error("Caught a JMS Exception while sending a status request", e);
		}
	}

	/**
	 * @return the lastRunTimestamp value
	 */
	public static long getLastRunTimestamp() {
		return lastRunTimestamp.get();
	}
	
	/**
	 * Sets the timeStamp value to the current time in milliseconds
	 */
	private void updateLastRunTimestamp() {
		lastRunTimestamp.set(System.currentTimeMillis());
	}

	/**
	 * @return the statusRequestProducer
	 */
	public StatusRequestProducer getStatusRequestProducer() {
		return statusRequestProducer;
	}
}
