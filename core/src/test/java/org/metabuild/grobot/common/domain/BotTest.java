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
package org.metabuild.grobot.common.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.metabuild.grobot.common.domain.Bot;
import org.metabuild.grobot.common.jms.StatusResponse;

/**
 * @author jburbridge
 * @since 10/13/2012
 */
public class BotTest {

	/**
	 * Test method for {@link org.metabuild.grobot.common.domain.Bot#TargetHost(java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	public void testBotStringStringBoolean() {
		Bot bot = new Bot("foo","bar", true);
		assertEquals("foo", bot.getName());
		assertEquals("bar", bot.getAddress());
		assertTrue(bot.isActive());
	}

	/**
	 * Test method for {@link org.metabuild.grobot.common.domain.Bot#TargetHost(org.metabuild.grobot.common.jms.StatusResponse)}.
	 */
	@Test
	public void testBotStatusResponse() {
		StatusResponse statusResponse = new StatusResponse();
		Bot bot = new Bot(statusResponse);
		assertEquals(statusResponse.getHostname(),bot.getName());
		assertEquals(statusResponse.getSystemProperties(),bot.getSystemProperties());
		assertEquals(statusResponse.getOtherProperties(),bot.getCustomProperties());
		assertTrue(bot.isActive());
	}

	/**
	 * Test method for {@link org.metabuild.grobot.common.domain.Bot#isActive()}.
	 */
	@Test
	public void testIsActive() {
		Bot activeBot = new Bot("foo","bar", true);
		assertTrue(activeBot.isActive());
		Bot innactiveBot = new Bot("foo","bar", false);
		assertFalse(innactiveBot.isActive());
	}

}
