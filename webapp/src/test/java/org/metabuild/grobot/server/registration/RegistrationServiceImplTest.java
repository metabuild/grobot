package org.metabuild.grobot.server.registration;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;
import javax.jms.Message;

import org.junit.Test;
import org.metabuild.grobot.server.mq.FakeTargetHostServiceImpl;
import org.metabuild.grobot.server.service.TargetHostService;

public class RegistrationServiceImplTest {

	@Test
	public void testHandleJmsRequest() {
		
		TargetHostService targetHostService = new FakeTargetHostServiceImpl();
		RegistrationResponseProducer registrationResponseProducer = mock(RegistrationResponseProducerImpl.class);
		Message message = mock(Message.class);
		try {
			when(message.getStringProperty("hostname")).thenReturn("valid.fakehost1");
			RegistrationService registrationService = new RegistrationServiceImpl(targetHostService, registrationResponseProducer);
			registrationService.handleRegistrationRequest(message);
		} catch (JMSException e) {
			fail("Should not have thrown an exception");
		}
	}

}
