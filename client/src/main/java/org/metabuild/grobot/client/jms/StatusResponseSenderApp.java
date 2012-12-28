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
package org.metabuild.grobot.client.jms;

import javax.jms.JMSException;

import org.metabuild.grobot.client.config.ClientJmsConfig;
import org.metabuild.grobot.client.status.StatusResponseProducerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Test app to send messages to the mq
 * @author jburbridge
 * @since 10/11/2012
 */
public class StatusResponseSenderApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusResponseSenderApp.class);

	public static void main(String[] args) throws JMSException, InterruptedException {
		LOGGER.info("Sending status responses to queue");
		ApplicationContext context = new AnnotationConfigApplicationContext(ClientJmsConfig.class);
		StatusResponseProducerImpl producer = context.getBean(StatusResponseProducerImpl.class);
		int x = 0;
		while (x++ < 10) {
			producer.sendStatusResponse();
			Thread.sleep(1000);
		}
	}
}
