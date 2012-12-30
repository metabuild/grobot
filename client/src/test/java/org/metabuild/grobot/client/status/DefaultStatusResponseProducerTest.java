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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;

import org.junit.Test;
import org.metabuild.net.HostnameResolver;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author jburbridge
 * @since 10/13/2012
 */
public class DefaultStatusResponseProducerTest {

	/**
	 * Test method for {@link org.metabuild.grobot.client.status.StatusResponseProducerImpl#sendStatusResponse()}.
	 * @throws JMSException
	 */
	@Test
	public void testSendStatus() throws JMSException {
		StatusResponseProducer producer = new StatusResponseProducerImpl(getMockHostnameResolver("fooooey"));
		producer.setJmsTemplate(getMockJmsTemplate());
		producer.sendStatusResponse();
		// TODO: need to figure out how to test these
	}

	private JmsTemplate getMockJmsTemplate() {
		JmsTemplate jmsTemplate = mock(JmsTemplate.class);
		return jmsTemplate;
	}
	private HostnameResolver getMockHostnameResolver(String hostname) {
		HostnameResolver resolver = mock(HostnameResolver.class);
		when(resolver.getHostname()).thenReturn(hostname);
		return resolver;
	}
}
