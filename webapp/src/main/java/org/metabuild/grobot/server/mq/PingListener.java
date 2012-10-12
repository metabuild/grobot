package org.metabuild.grobot.server.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.metabuild.grobot.domain.TargetCache;
import org.metabuild.grobot.domain.TargetHost;
import org.metabuild.grobot.mq.PingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jburbridge
 *
 */
@Component
public class PingListener implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingListener.class);
	
	@Autowired
	private TargetCache targetCache;
	
	public PingListener() {
		LOGGER.info("Initializing {}...", PingListener.class.getName());
	}
	
	/**
	 * Constructor with DI for unit testing
	 * @param targetCache
	 */
	protected PingListener(TargetCache targetCache) {
		this();
		this.targetCache = targetCache;
	}
	
	@Override
	public void onMessage(Message message) {
		try {
			PingResponse pingResponse = (PingResponse) ((ObjectMessage) message);
			String hostname = message.getStringProperty("hostname");
			if (hostname != null && targetCache.get(hostname) == null) {
				LOGGER.info("Adding {} to targetCache", hostname);
				targetCache.put(hostname, new TargetHost(hostname, hostname, true));
			}
			LOGGER.info("Received ping response \"{}\" from {}", pingResponse, hostname);
		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	protected TargetCache getTargetCache() {
		return targetCache;
	}
}
