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
