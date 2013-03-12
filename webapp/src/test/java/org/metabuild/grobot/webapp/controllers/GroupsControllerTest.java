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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.metabuild.grobot.AbstractSpringEnabledTest;
import org.metabuild.grobot.common.domain.TargetGroup;
import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.server.repository.TargetGroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

/**
 * 
 * @author jburbridge
 *
 */
public class GroupsControllerTest extends AbstractSpringEnabledTest {
	
	private final List<TargetGroup> groups = new ArrayList<TargetGroup>();
	
	@Before
	public void initStatus() {
		groups.add(new TargetGroup("groupname1", new HashSet<TargetHost>()));
	}
	
	@Test
	public void testList() {
		
		@SuppressWarnings("unchecked")
		Page<TargetGroup> page = mock(Page.class);
		when(page.getContent()).thenReturn(groups);
		
		TargetGroupRepository targetGroupRepository = mock(TargetGroupRepository.class);
		when(targetGroupRepository.findAll(any(Pageable.class))).thenReturn(page);
		
		GroupsController controller = new GroupsController();
		
		ReflectionTestUtils.setField(controller, "targetGroupRepository", targetGroupRepository);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.list(uiModel, null);
		
		assertNotNull(result);
		assertEquals("groups/list", result);
		
		@SuppressWarnings("unchecked")
		Page<TargetGroup> modelPage = (Page<TargetGroup>) uiModel.get("page");
		
		assertEquals(1, modelPage.getContent().size());
	}
	
	@Test
	public void testDetail() {
		
		String randomUUId = UUID.randomUUID().toString();
		TargetGroupRepository targetGroupRepository = mock(TargetGroupRepository.class);
		when(targetGroupRepository.findById(randomUUId)).thenReturn(groups.get(0));
		
		GroupsController controller = new GroupsController();
		
		ReflectionTestUtils.setField(controller, "targetGroupRepository", targetGroupRepository);
		
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.details(randomUUId, uiModel);
		
		assertNotNull(result);
		assertEquals("groups/details", result);
		
		TargetGroup targetGroup = (TargetGroup) uiModel.get("group");
		assertEquals("groupname1", targetGroup.getName());
		assertEquals(0, targetGroup.getTargetHosts().size());
		assertTrue(targetGroup.isActive());

	}

}
