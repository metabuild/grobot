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
package org.metabuild.grobot.client.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.metabuild.grobot.client.key.FileBasedKeyManager;
import org.metabuild.grobot.client.key.KeyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:grobot-client.properties")
@Import(ClientJmsConfig.class)
public class ClientConfig {

	@Autowired
	Environment environment;

	@Bean(name="clientName")
	public String getClientName() throws UnknownHostException {
		String clientName = environment.getProperty("grobot.client.name") != null ?
				environment.getProperty("grobot.client.name") : InetAddress.getLocalHost().getCanonicalHostName();
				if (clientName == null) {
					throw new RuntimeException("Unable to determine the client name from properties or hostname");
				}
				return clientName;
	}

	@Bean(name="keyManager")
	public KeyManager getKeyManager() {
		return new FileBasedKeyManager();
	}
}
