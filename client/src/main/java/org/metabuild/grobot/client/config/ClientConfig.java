package org.metabuild.grobot.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:grobot.properties")
@Import(ClientJmsConfig.class)
public class ClientConfig {

	@Autowired
	Environment environment;
	
	@Bean(name="clientName")
	public String getClientName() {
		return environment.getProperty("grobot.client.name");
	}
}
