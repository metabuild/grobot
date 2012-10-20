package org.metabuild.grobot.server.config;


import org.metabuild.grobot.domain.FakeTargetCacheImpl;
import org.metabuild.grobot.domain.TargetHostCache;
import org.metabuild.grobot.webapp.domain.GreetingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Main spring application configuration
 * 
 * @author jburbridge
 * @since 9/30/2012
 */
@Configuration
@ComponentScan(basePackages = {"org.metabuild.grobot.webapp.domain"})
@PropertySource("classpath:grobot.properties")
public class AppConfig {

	@Autowired
	Environment environment;

	@Bean(name="targetCache")
	public TargetHostCache getTargetCache() {
		return new FakeTargetCacheImpl(15);
//		return new TargetCacheImpl();
	}

	@Bean
	public GreetingMessage greetingMessage() {
		return new GreetingMessage();
	}
	
	@Bean(name="serverHostname")
	public String getServerHostname() {
		return environment.getProperty("grobot.server.hostname");
	}
	
	@Bean(name="helloMessage")
	public String getHelloMessage() {
		return environment.getProperty("grobot.server.hello.message");
	}
	
//	@Profile("default")
//	@Configuration
//	class ProdAppConfig {
//		
//	}
//	
//	@Profile("testing")
//	@Configuration
//	class TestAppConfig {
//		
//		@Bean(name="targetCache")
//		public TargetCache getTargetCache() {
//			return new FakeTargetCacheImpl(15);
//		}
//	}
}
