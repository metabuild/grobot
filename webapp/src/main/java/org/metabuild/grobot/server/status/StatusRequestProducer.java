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

import org.springframework.jms.core.JmsTemplate;

/**
 * Classes implementing this interface send status request from the Grobot server by 
 * producing a StatusRequest message and putting into the message topic.
 * 
 * @author jburbridge
 * @since 10/11/2012
 */
public interface StatusRequestProducer {

	/**
	 * Sends the status request to the message topic
	 * @throws JMSException
	 */
	public void sendStatusRequest() throws JMSException;

	/**
	 * Allows for the jmsTemplate to be injected for unit testing
	 * @param jmsTemplate
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate);

}
