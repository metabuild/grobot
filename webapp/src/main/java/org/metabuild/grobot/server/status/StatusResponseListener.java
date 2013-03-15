/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.metabuild.grobot.server.status;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.joda.time.DateTime;
import org.metabuild.grobot.common.domain.Bot;
import org.metabuild.grobot.common.jms.StatusResponse;
import org.metabuild.grobot.common.jms.StatusResponseMessageConverter;
import org.metabuild.grobot.server.service.BotService;
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
	
	private final BotService botService;
	private final StatusResponseMessageConverter messageConverter;

	
	/**
	 * Constructor with DI for unit testing
	 * @param targetHostService
	 */
	public StatusResponseListener(BotService targetHostService) {
		LOGGER.info("Initializing {}...", this.getClass().getName());
		this.messageConverter = new StatusResponseMessageConverter();
		this.botService = targetHostService;
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
				Bot bot = botService.findByName(hostname);
				if (bot != null) {
					botService.findByName(hostname).setLastUpdatedStatus(new DateTime(statusResponse.getTimeStamp()));
				} else {
					LOGGER.warn("Unregistered bot {} is listening on status topic!", hostname);
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
	protected BotService getBotService() {
		return botService;
	}
}
