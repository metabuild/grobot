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
package org.metabuild.grobot.server.registration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import org.metabuild.grobot.common.jms.RegistrationData;
import org.metabuild.grobot.server.mq.FakeTargetHostServiceImpl;
import org.metabuild.grobot.server.service.TargetHostService;

/**
 * @author jburbridge
 * @since 11/19/2012
 */
public class RegistrationServiceImplTest {

	@Test
	public void testHandleJmsRequest() {
		
		TargetHostService targetHostService = new FakeTargetHostServiceImpl();
		RegistrationResponseProducer registrationResponseProducer = mock(RegistrationResponseProducerImpl.class);
		RegistrationData registrationDetails = mock(RegistrationData.class);
		when(registrationDetails.getHostname()).thenReturn("valid.fakehost1");
		RegistrationService registrationService = new RegistrationServiceImpl(targetHostService, registrationResponseProducer);
		registrationService.processRegistrationRequest(registrationDetails, null);
	}

}
