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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.metabuild.grobot.common.domain.Bot;
import org.metabuild.grobot.common.jms.RegistrationData;
import org.metabuild.grobot.server.registration.RegistrationService;

public class FakeBotRegistrationManagerImpl implements
		RegistrationService {

	private static final Map<String,Bot> bots = new HashMap<String,Bot>();
	
	protected void createUnregistered(Bot targetHost) {
		bots.put(targetHost.getName(), targetHost);
	}

	protected List<Bot> getPendingRegistrations() {
		return new ArrayList<Bot>(bots.values());
	}

	@Override
	public RegistrationData processRegistrationRequest(RegistrationData registrationDetails, Destination replDestination) {
		final Bot bot = new Bot(registrationDetails.getHostname(), registrationDetails.getHostname(), true);
		createUnregistered(bot);
		registrationDetails.setKey(bot.getId());
		return registrationDetails;
	}
}
