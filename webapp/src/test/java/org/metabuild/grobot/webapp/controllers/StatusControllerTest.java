package org.metabuild.grobot.webapp.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import org.metabuild.grobot.domain.TargetHost;
import org.metabuild.grobot.domain.TargetHostCache;
import org.metabuild.grobot.domain.TargetHostCacheImpl;
import org.metabuild.grobot.server.mq.StatusRequestProducer;
import org.metabuild.grobot.server.mq.StatusRequestProducerImpl;

/**
 * @author jburbridge
 *
 */
public class StatusControllerTest extends BaseControllerTest {

	private final List<TargetHost> targets = new ArrayList<TargetHost>();
	
	@Before
	public void initStatus() {
		targets.add(new TargetHost("hostname1", "hostaddress1", true));
	}
	
	@Test
	public void testList() {
		
		TargetHostCache targetHostCache = mock(TargetHostCacheImpl.class);
		when(targetHostCache.getAll()).thenReturn(targets);
		
		StatusRequestProducer producer = mock(StatusRequestProducerImpl.class);
		
		BotsController controller = new BotsController();
		
		ReflectionTestUtils.setField(controller, "targetHostCache", targetHostCache);
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
		
		TargetHostCache targetHostCache = mock(TargetHostCacheImpl.class);
		when(targetHostCache.get(anyString())).thenReturn(targets.get(0));
		
		StatusRequestProducer producer = mock(StatusRequestProducerImpl.class);
		
		BotsController controller = new BotsController();
		
		ReflectionTestUtils.setField(controller, "targetHostCache", targetHostCache);
		ReflectionTestUtils.setField(controller, "producer", producer);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.details("hostname1", uiModel);
		
		assertNotNull(result);
		assertEquals("bots/details", result);
		
		TargetHost targetHost = (TargetHost) uiModel.get("target");
		assertEquals("hostname1", targetHost.getName());
		assertEquals("hostaddress1", targetHost.getAddress());
		assertTrue(targetHost.isActive());

	}

}
