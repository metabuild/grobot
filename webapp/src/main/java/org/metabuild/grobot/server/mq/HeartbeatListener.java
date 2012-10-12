package org.metabuild.grobot.server.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.metabuild.grobot.domain.TargetCache;
import org.metabuild.grobot.domain.TargetHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jburbridge
 *
 */
@Component
public class HeartbeatListener implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatListener.class);
	
	@Autowired
	private TargetCache targetCache;
	
	public HeartbeatListener() {
		LOGGER.info("Initializing HeartbeatListener...");
	}
	
	/**
	 * Constructor with DI for unit testing
	 * @param targetCache
	 */
	protected HeartbeatListener(TargetCache targetCache) {
		this();
		this.targetCache = targetCache;
	}
	
	@Override
	public void onMessage(Message message) {
		try {
			String body = ((TextMessage) message).getText();
			String hostname = message.getStringProperty("hostname");
			if (hostname != null && targetCache.get(hostname) == null) {
				LOGGER.info("Adding {} to targetCache", hostname);
				targetCache.put(hostname, new TargetHost(hostname, hostname, true));
			}
			LOGGER.info("Received heartbeat \"{}\" from {}", body, hostname);
		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	protected TargetCache getTargetCache() {
		return targetCache;
	}
}
