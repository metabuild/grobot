package org.metabuild.grobot.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author jburbridge
 * @since 10/6/2012
 */
@Service
public class SimpleRequestProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMessageListener.class);
	
	@Transactional
	public void process(String payload) {
		LOGGER.info("Logging the request: {}", payload);
	}
}
