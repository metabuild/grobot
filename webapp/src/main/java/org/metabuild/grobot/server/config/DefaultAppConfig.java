package org.metabuild.grobot.server.config;

import java.io.File;
import java.io.IOException;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

import org.metabuild.grobot.tasks.BindingProvider;
import org.metabuild.grobot.tasks.groovy.GroovyBindingProvider;
import org.metabuild.grobot.tasks.groovy.GroovyTaskCache;
import org.metabuild.grobot.tasks.groovy.GroovyTaskFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Common spring application configuration
 * 
 * @author jburbridge
 * @since 9/30/2012
 */
@Configuration
@Profile("default")
@Import({ 
	DevelopmentAppConfig.class, 
	ProductionAppConfig.class,
	HibernateConfig.class
})
@ComponentScan(basePackages = { "org.metabuild.grobot.webapp.domain" })
@PropertySource("classpath:grobot.properties")
public class DefaultAppConfig {

	@Autowired
	Environment environment;

	/**
	 * @return the directory where groovy tasks are saved
	 */
	@Bean(name="tasksDir")
	public String getTasksDir() {
		return new StringBuilder(environment.getProperty("user.dir"))
			.append(File.separator)
			.append("tasks")
			.toString();
	}
	
	/**
	 * @param tasksDir the directory where groovy tasks are located
	 * @return the GroovyScriptEngine
	 * @throws IOException
	 */
	@Autowired(required=true)
	@Bean(name="GroovyScriptEngine")
	public GroovyScriptEngine getGroovyScriptEngine(@Qualifier(value="tasksDir") String tasksDir) 
			throws IOException {
		return new GroovyScriptEngine(tasksDir);
	}
	
	/**
	 * @return the GroovyBindingProvider
	 */
	@Bean(name="bindingProvider")
	public BindingProvider getBindingProvider() {
		return new GroovyBindingProvider(System.getProperties(), new Binding());
	}
	
	/**
	 * @param bindingProvider
	 * @param tasksDir
	 * @param groovyScriptEngine
	 * @return the in-memory cache for tasks
	 */
	@Autowired(required=true)
	@Bean(name="groovyTaskCache")
	public GroovyTaskCache getGroovyTaskCache(BindingProvider bindingProvider, String tasksDir, GroovyScriptEngine groovyScriptEngine) {
		final GroovyTaskFactory groovyTaskFactory = new GroovyTaskFactory(bindingProvider, new File(tasksDir), groovyScriptEngine);
		return new GroovyTaskCache(groovyTaskFactory);
	}
	
	/**
	 * @return the server's fqdn
	 */
	@Bean(name="serverHostname")
	public String getServerHostname() {
		return environment.getProperty("grobot.server.hostname");
	}
}
