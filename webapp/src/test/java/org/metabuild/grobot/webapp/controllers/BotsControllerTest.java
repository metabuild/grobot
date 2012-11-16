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
import org.metabuild.grobot.server.dao.TargetHostDaoImpl;
import org.metabuild.grobot.server.mq.StatusRequestProducer;
import org.metabuild.grobot.server.mq.StatusRequestProducerImpl;
import org.metabuild.grobot.webapp.AbstractSpringEnabledTest;

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
		
		TargetHostDaoImpl targetHostDao = mock(TargetHostDaoImpl.class);
		when(targetHostDao.findAll()).thenReturn(targets);
		
		StatusRequestProducer producer = mock(StatusRequestProducerImpl.class);
		
		BotsController controller = new BotsController();
		
		ReflectionTestUtils.setField(controller, "targetHostDao", targetHostDao);
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
		
		TargetHostDaoImpl targetHostDao = mock(TargetHostDaoImpl.class);
		when(targetHostDao.find(anyLong())).thenReturn(targets.get(0));
		
		StatusRequestProducer producer = mock(StatusRequestProducerImpl.class);
		
		BotsController controller = new BotsController();
		
		ReflectionTestUtils.setField(controller, "targetHostDao", targetHostDao);
		ReflectionTestUtils.setField(controller, "producer", producer);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.details(1L, uiModel);
		
		assertNotNull(result);
		assertEquals("bots/details", result);
		
		TargetHost targetHost = (TargetHost) uiModel.get("target");
		assertEquals("hostname1", targetHost.getName());
		assertEquals("hostaddress1", targetHost.getAddress());
		assertTrue(targetHost.isActive());

	}

}
