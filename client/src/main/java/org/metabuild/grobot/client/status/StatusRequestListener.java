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
package org.metabuild.grobot.client.status;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.metabuild.grobot.common.jms.StatusMessageType;
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
