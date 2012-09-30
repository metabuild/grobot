package org.metabuild.grobot.server.config;

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
@ComponentScan("org.metabuild.grobot.server.beans")
@PropertySource("file:${user.home}/.grobot/grobot.properties")
public class AppConfig {

	@Autowired
	Environment env;
	
}