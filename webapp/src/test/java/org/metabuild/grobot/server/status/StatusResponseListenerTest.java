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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.junit.Test;
import org.metabuild.grobot.common.jms.StatusResponse;
import org.metabuild.grobot.server.mq.FakeTargetHostServiceImpl;

/**
 * @author jburbridge
 * @since 10/13/2012
 */
public class StatusResponseListenerTest {

	/**
	 * @throws JMSException
	 */
	@Test
	public void testReceiveStatusResponseFromKnownTarget() throws JMSException {

		/*
		 * Doesn't seem like there's much to test other than the indirect effects from receiving 
		 * different status response messages which can be verified by inspecting the TargetHostService
		 * 
		 */
		StatusResponseListener listener = new StatusResponseListener(new FakeTargetHostServiceImpl());
		
		listener.onMessage(getMockObjectMessage("valid1"));
		assertEquals(1,listener.getTargetHostService().findAll().size());
		
		listener.onMessage(getMockObjectMessage("valid2"));
		assertEquals(2,listener.getTargetHostService().findAll().size());
		
		// duplicate message
		listener.onMessage(getMockObjectMessage("valid2"));
		assertEquals(2,listener.getTargetHostService().findAll().size());
		
		// invalid host that hasn't been registered yet
		listener.onMessage(getMockObjectMessage("invalid1"));
		assertEquals(2,listener.getTargetHostService().findAll().size());

	}

	private ObjectMessage getMockObjectMessage(String hostname) throws JMSException {
		ObjectMessage objectMessage = mock(ObjectMessage.class);
		when(objectMessage.getObject()).thenReturn(new StatusResponse(hostname, null, null));
		return objectMessage;
	}
}
