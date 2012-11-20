package org.metabuild.grobot.webapp.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.metabuild.grobot.domain.TargetHost;
import org.metabuild.grobot.server.service.TargetHostService;
import org.metabuild.grobot.server.service.TargetHostServiceImpl;
import org.metabuild.grobot.server.status.StatusRequestProducer;
import org.metabuild.grobot.server.status.StatusRequestProducerImpl;
import org.metabuild.grobot.webapp.AbstractSpringEnabledTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

/**
 * @author jburbridge
 *
 */
public class BotsControllerTest extends AbstractSpringEnabledTest {

	private final List<TargetHost> targets = new ArrayList<TargetHost>();
	
	@Before
	public void initStatus() {
		targets.add(new TargetHost("hostname1", "hostaddress1", true));
	}
	
	@Test
	public void testList() {
		
		TargetHostService targetHostService = mock(TargetHostServiceImpl.class);
		when(targetHostService.findAll()).thenReturn(targets);
		
		StatusRequestProducer producer = mock(StatusRequestProducerImpl.class);
		
		BotsController controller = new BotsController();
		
		ReflectionTestUtils.setField(controller, "targetHostService", targetHostService);
		ReflectionTestUtils.setField(controller, "producer", producer);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.list(uiModel);
		
		assertNotNull(result);
		assertEquals("bots/list", result);
		
		@SuppressWarnings("unchecked")
		List<TargetHost> modelTargetHosts = (List<TargetHost>) uiModel.get("targets");
		
		assertEquals(1, modelTargetHosts.size());
	}
	
	@Test
	public void testDetail() {
		
		TargetHostService targetHostService = mock(TargetHostServiceImpl.class);
		when(targetHostService.find(anyString())).thenReturn(targets.get(0));
		
		StatusRequestProducer producer = mock(StatusRequestProducerImpl.class);
		
		BotsController controller = new BotsController();
		
		ReflectionTestUtils.setField(controller, "targetHostService", targetHostService);
		ReflectionTestUtils.setField(controller, "producer", producer);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.details(UUID.randomUUID().toString(), uiModel);
		
		assertNotNull(result);
		assertEquals("bots/details", result);
		
		TargetHost targetHost = (TargetHost) uiModel.get("target");
		assertEquals("hostname1", targetHost.getName());
		assertEquals("hostaddress1", targetHost.getAddress());
		assertTrue(targetHost.isActive());

	}

}
