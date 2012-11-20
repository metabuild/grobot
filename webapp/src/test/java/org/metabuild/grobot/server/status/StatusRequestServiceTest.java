package org.metabuild.grobot.server.status;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.jms.JMSException;

import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author jburbridge
 * @since 11/17/2012
 */
public class StatusRequestServiceTest {

	@Test
	public void testRunAndTimestamps() {
		StatusRequestProducer fakeStatusRequestProducer = new FakeStatusRequestProducer();
		StatusRequestService statusRequestService = new StatusRequestService(fakeStatusRequestProducer);
		try {
			long beforeStamp = StatusRequestService.getLastRunTimestamp();
			statusRequestService.run();
			long afterStamp = StatusRequestService.getLastRunTimestamp();
			assertTrue(beforeStamp < afterStamp);
		} catch (Exception e) {
			fail("Should have succeeded but caught an excetion");
		}
	}

	private class FakeStatusRequestProducer implements StatusRequestProducer {
		@Override
		public void sendStatusRequest() throws JMSException {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void setJmsTemplate(JmsTemplate jmsTemplate) {}
	}
}
