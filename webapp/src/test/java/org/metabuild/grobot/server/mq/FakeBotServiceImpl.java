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
package org.metabuild.grobot.server.mq;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.metabuild.grobot.common.domain.Bot;
import org.metabuild.grobot.server.service.BotService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author jburbridge
 * @since 11/17/2012
 */
public class FakeBotServiceImpl implements BotService {
	
	private final Map<String,Bot> bots = new HashMap<String, Bot>();


	@Override
	public List<Bot> findAll() {
		return new ArrayList<Bot>(bots.values());
	}

	@Override
	public Page<Bot> findAll(Pageable pageable) {
		Page mockPage = mock(Page.class);
		when(mockPage.getContent()).thenReturn(new ArrayList<Bot>(bots.values()));
		return mockPage;
	}

	/*
	 * Creates new target hosts on the fly if their names start with "valid" and adds them to 
	 * the cache, otherwise return null
	 */
	@Override
	public Bot findByName(String hostname) {
		Bot target = null;
		if (bots.containsKey(hostname)) {
			target = bots.get(hostname);
		} else if (hostname != null && hostname.startsWith("valid")) {
			target = new Bot(hostname, hostname + ".fake.address", true);
			bots.put(hostname, target);
		} 
		return target;
	}

	@Override
	public List<Bot> findAllWithProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bot findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bot update(Bot bot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Bot bot) {
		// TODO Auto-generated method stub

	}

	@Override
	public Bot create(Bot bot) {
		// TODO Auto-generated method stub
		return null;
	}

}
