package org.metabuild.grobot.server.mq;

//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.ObjectMessage;
//import javax.jms.TextMessage;

import org.metabuild.grobot.domain.TargetCache;
import org.metabuild.grobot.domain.TargetHost;
import org.metabuild.grobot.mq.PingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jms.core.support.JmsGatewaySupport;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
@Component
public class PingResponseListener extends JmsGatewaySupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingResponseListener.class);
	
	@Autowired
	private TargetCache targetCache;
	
	public PingResponseListener() {
		LOGGER.info("Initializing {}...", PingResponseListener.class.getName());
	}
	
	/**
	 * Constructor with DI for unit testing
	 * @param targetCache
	 */
	protected PingResponseListener(TargetCache targetCache) {
		this();
		this.targetCache = targetCache;
	}
	
	public void receivePingResponse() {
		PingResponse pingResponse = (PingResponse) getJmsTemplate().receiveSelectedAndConvert("ping = 'response'");
		String hostname = pingResponse.getHostname();
		if (hostname != null && targetCache.get(hostname) == null) {
			LOGGER.info("Adding {} to targetCache", hostname);
			targetCache.put(hostname, new TargetHost(hostname, hostname, true));
		}
		LOGGER.info("Received ping response \"{}\" from {}", pingResponse, hostname);
	}
	
	protected TargetCache getTargetCache() {
		return targetCache;
	}
}
