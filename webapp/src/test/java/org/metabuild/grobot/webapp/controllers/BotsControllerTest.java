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
package org.metabuild.grobot.webapp.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.metabuild.grobot.AbstractSpringEnabledTest;
import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.server.repository.TargetHostRepository;
import org.metabuild.grobot.server.service.TargetHostService;
import org.metabuild.grobot.server.service.TargetHostServiceImpl;
import org.metabuild.grobot.server.status.StatusRequestProducer;
import org.metabuild.grobot.server.status.StatusRequestProducerImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		
		@SuppressWarnings("unchecked")
		Page<TargetHost> page = mock(Page.class);
		when(page.getContent()).thenReturn(targets);
		
		TargetHostRepository targetHostRepository = mock(TargetHostRepository.class);
		when(targetHostRepository.findAll(any(Pageable.class))).thenReturn(page);
		
		StatusRequestProducer producer = mock(StatusRequestProducerImpl.class);
		
		BotsController controller = new BotsController();
		
		ReflectionTestUtils.setField(controller, "targetHostRepository", targetHostRepository);
		ReflectionTestUtils.setField(controller, "producer", producer);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.list(uiModel, null);
		
		assertNotNull(result);
		assertEquals("bots/list", result);
		
		@SuppressWarnings("unchecked")
		Page<TargetHost> modelPage = (Page<TargetHost>) uiModel.get("page");
		
		assertEquals(1, modelPage.getContent().size());
	}
	
	@Test
	public void testDetail() {
		
		String randomUUId = UUID.randomUUID().toString();
		TargetHostRepository targetHostRepository = mock(TargetHostRepository.class);
		when(targetHostRepository.findById(randomUUId)).thenReturn(targets.get(0));
		
		StatusRequestProducer producer = mock(StatusRequestProducerImpl.class);
		
		BotsController controller = new BotsController();
		
		ReflectionTestUtils.setField(controller, "targetHostRepository", targetHostRepository);
		ReflectionTestUtils.setField(controller, "producer", producer);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.details(randomUUId, uiModel);
		
		assertNotNull(result);
		assertEquals("bots/details", result);
		
		TargetHost targetHost = (TargetHost) uiModel.get("target");
		assertEquals("hostname1", targetHost.getName());
		assertEquals("hostaddress1", targetHost.getAddress());
		assertTrue(targetHost.isActive());

	}

}
