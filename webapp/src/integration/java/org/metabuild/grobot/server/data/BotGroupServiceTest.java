package org.metabuild.grobot.server.data;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.metabuild.grobot.common.domain.BotGroup;
import org.metabuild.grobot.server.service.BotGroupService;
import org.springframework.beans.factory.annotation.Autowired;

public class BotGroupServiceTest extends AbstractDataTester {

	@Autowired
	private BotGroupService service;
	
	@Test
	public void testFindAll() {
		List<BotGroup> groups = service.findAll();
		assertEquals(1, groups.size());
		assertEquals(1, groups.get(0).getBots().size());
	}
	
	@Test
	public void testFindById() {
		BotGroup group = service.findById("225c172b-950f-4c24-9ee7-4d3b4ea1cf55");
		assertNotNull(group);
		assertEquals("groupName", group.getName());
		assertTrue(group.isActive());
		assertNull(service.findById("invalid-id"));
	}
}
