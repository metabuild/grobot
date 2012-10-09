package org.metabuild.grobot.webapp.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.metabuild.grobot.webapp.domain.GreetingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;


/**
 * Main spring application configuration
 * 
 * @author jburbridge
 * @since 9/30/2012
 */
@Configuration
@ComponentScan(basePackages = {"org.metabuild.grobot.webapp", "org.metabuild.grobot.webapp.domain"})
@PropertySource("file:${user.home}/.grobot/grobot.properties")
public class AppConfig {

	@Autowired
	Environment environment;
	
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
	
	@Bean(name="activeMqUri")
	public String getServerActiveMqUri() {
		return environment.getProperty("grobot.server.activemq.uri");
	}

	@Bean(name="jmsConnectionFactory")
	public ConnectionFactory getJmsConnectionFactory(String activeMqUri) {
	    final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
	    factory.setBrokerURL(activeMqUri);
	    return factory;
	}

	@Bean(name="requestsQueue")
	public Queue requestsQueue() {
	    return new ActiveMQQueue("requests");
	}

	@Autowired(required=true)
	@Bean(name="jmsTemplate")
	public JmsOperations jmsOperations(ConnectionFactory jmsConnectionFactory, Queue requestsQueue) {
	    final JmsTemplate jmsTemplate = new JmsTemplate(jmsConnectionFactory);
	    jmsTemplate.setDefaultDestination(requestsQueue);
	    return jmsTemplate;
	}

//	@Resource 
//	private SimpleRequestProcessor requestProcessor;
//	
//	@Bean(name="jsmConnectionFactory")
//	public ConnectionFactory jmsConnectionFactory(String activeMqUri) {
//	    final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
//	    factory.setBrokerURL(activeMqUri);
//	    return new PooledConnectionFactory(factory);
//	}
//	 
//	@Bean
//	public Queue requestsQueue() {
//	    return new ActiveMQQueue("requests");
//	}
//	 
//	@Bean
//	public JmsOperations jmsOperations() {
//	    final JmsTemplate jmsTemplate = new JmsTemplate(jmsConnectionFactory());
//	    jmsTemplate.setDefaultDestination(requestsQueue());
//	    return jmsTemplate;
//	}
//	
//	@Bean
//	public AbstractJmsListeningContainer jmsContainer() {
//	    final DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//	    container.setConnectionFactory(jmsConnectionFactory());
//	    container.setDestination(requestsQueue());
//	    container.setSessionTransacted(true);
//	    container.setConcurrentConsumers(5);
//	    container.setMessageListener(messageListenerAdapter());
//	    return container;
//	}
//	 
//	private MessageListenerAdapter messageListenerAdapter() {
//	    final MessageListenerAdapter adapter = new MessageListenerAdapter(requestProcessor);
//	    adapter.setDefaultListenerMethod("process");
//	    return adapter;
//	}
}