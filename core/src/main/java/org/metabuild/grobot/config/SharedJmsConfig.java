package org.metabuild.grobot.config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.metabuild.grobot.mq.StatusResponseMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@PropertySource("file:${user.home}/.grobot/grobot.properties")
public class SharedJmsConfig {

	@Autowired
	Environment environment;
	
	@Bean(name="activeMqUri")
	public String getActiveMqUri() {
		String activeMqUri = environment.getProperty("grobot.server.activemq.uri");
		return activeMqUri;
	}
	
	@Bean(name="jmsConnectionFactory")
	public ConnectionFactory getJmsConnectionFactory(String activeMqUri) {
		final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(activeMqUri);
		return factory;
	}

	@Autowired(required=true)
	@Bean(name="statusTopicName")
	public String getStatusTopicName() {
		return environment.getProperty("grobot.status.topic");
	}
	
	@Autowired(required=true)
	@Qualifier(value="statusTopicName")
	@Bean(name="statusTopicDestination")
	public Destination getStatusTopicDestination(String statusTopicName) {
		return new ActiveMQTopic(statusTopicName);
	}

	@Autowired(required=true)
	@Bean(name="statusTopicJmsTemplate")
	public JmsTemplate getStatusTopicJmsTemplate(ConnectionFactory connectionFactory, 
			@Qualifier(value="statusTopicDestination") Destination statusTopicDestination, 
			MessageConverter messageConverter) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setDefaultDestination(statusTopicDestination);
		return jmsTemplate;
	}

	@Bean(name="statusResponseMessageConverter")
	public MessageConverter getStatusResponseMessageConverter() {
		return new StatusResponseMessageConverter();
	}

	@Bean(name="statusQueueDestination")
	public Destination getStatusQueueDestination() {
		final String queueName = environment.getProperty("grobot.status.queue");
		return new ActiveMQQueue(queueName);
	}
	
	@Autowired(required=true)
	@Bean(name="statusQueueJmsTemplate")
	public JmsTemplate getStatusQueueJmsTemplate(ConnectionFactory connectionFactory, 
			@Qualifier(value="statusQueueDestination") Destination statusQueueDestination, 
			MessageConverter messageConverter) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setDefaultDestination(statusQueueDestination);
		jmsTemplate.setMessageConverter(messageConverter);
		return jmsTemplate;
	}
}
