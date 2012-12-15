package org.metabuild.grobot.client.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.metabuild.grobot.client.key.KeyManager;
import org.metabuild.grobot.client.key.FileBasedKeyManager;
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
