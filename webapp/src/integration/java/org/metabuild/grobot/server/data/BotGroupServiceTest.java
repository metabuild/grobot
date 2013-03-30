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
package org.metabuild.grobot.server.data;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.metabuild.grobot.common.domain.Bot;
import org.metabuild.grobot.common.domain.BotGroup;
import org.metabuild.grobot.server.service.BotGroupService;
import org.metabuild.grobot.server.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TransactionConfiguration(defaultRollback=true)
public class BotGroupServiceTest extends AbstractDataTester {

	@Autowired
	private BotGroupService service;
	
	@Autowired
	private BotService botService;
	
	@Test
	public void testFindAll() {
		List<BotGroup> groups = service.findAll();
		assertTrue(groups.size() >= 1);
		assertTrue(groups.get(0).getBots().size() >= 1);
	}
	
	@Test
	public void testFindById() {
		BotGroup group = service.findById("225c172b-950f-4c24-9ee7-4d3b4ea1cf55");
		assertNotNull(group);
		assertEquals("groupName", group.getName());
		assertTrue(group.isActive());
		assertNull(service.findById("invalid-id"));
	}
	
	@Test
	public void testCreateWithBots() {
		Bot newBot1 = new Bot("botName1", "bot-address1", true);
		Bot newBot2 = new Bot("botName2", "bot-address2", true);
		Bot newBot3 = new Bot("botName3", "bot-address3", true);
		assertNotNull(botService.save(newBot1));
		assertNotNull(botService.save(newBot2));
		assertNotNull(botService.save(newBot3));
		Set<Bot> bots = new HashSet<Bot>();
		bots.add(newBot1);
		bots.add(newBot2);
		bots.add(newBot3);
		BotGroup newGroup = new BotGroup("newBotGroupName1", bots, null, true);
		newGroup = service.save(newGroup);
		assertNotNull(newGroup.getId());
		assertEquals(3, newGroup.getBots().size());
	}
	
	@Test
	public void testUpdateWithBots() {
		BotGroup newGroup = new BotGroup("newBotGroupName2", new HashSet<Bot>(), null, true);
		service.save(newGroup);
		assertNotNull(newGroup.getId());
		assertEquals(0, newGroup.getBots().size());
		
		Bot bot4 = new Bot("botName4", "bot-address4", true);
		Bot bot5 = new Bot("botName5", "bot-address5", true);
		Bot bot6 = new Bot("botName6", "bot-address6", true);
		newGroup.addBot(bot4);
		newGroup.addBot(bot5);
		newGroup.addBot(bot6);
		assertNotNull(botService.save(bot4));
		assertNotNull(botService.save(bot5));
		assertNotNull(botService.save(bot6));
		service.save(newGroup);
		assertEquals(3, newGroup.getBots().size());

		// reload from database
		bot4 = botService.findById(bot4.getId());
		assertEquals(1, bot4.getBotGroups().size());

		newGroup.removeBot(bot6);
		service.save(newGroup);
		botService.save(bot6);
		assertEquals(2, newGroup.getBots().size());
		
		// reload from database
		bot6 = botService.findById(bot6.getId());
		assertEquals(0, bot6.getBotGroups().size());
		
	}

}
