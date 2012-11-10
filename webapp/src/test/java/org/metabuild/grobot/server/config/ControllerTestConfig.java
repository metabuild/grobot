package org.metabuild.grobot.server.config;

import org.metabuild.grobot.domain.FakeTargetHostCacheImpl;
import org.metabuild.grobot.domain.TargetHostCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ControllerTestConfig {

	@Bean(name="targetCache")
	public TargetHostCache getTargetCache() {
		return new FakeTargetHostCacheImpl(15);
	}
}
