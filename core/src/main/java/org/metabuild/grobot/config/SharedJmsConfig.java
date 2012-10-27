package org.metabuild.grobot.config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.metabuild.grobot.mq.StatusResponseMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

/**
 * Shared JMS Configuration
 * 
 * @author jburbridge
 * @since 10/13/2012
 */
@Configuration
@PropertySource("classpath:grobot.properties")
public class SharedJmsConfig {

	@Autowired
	Environment environment;
	
	/**
	 * @return the connection factory
	 */
	@Bean(name="jmsConnectionFactory")
	public ConnectionFactory getJmsConnectionFactory() {
		final String activeMqUri = environment.getProperty("grobot.server.activemq.uri");
		final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(activeMqUri);
		return factory;
	}

	/**
	 * @return the response message converter
	 */
	@Bean(name="statusResponseMessageConverter")
	public MessageConverter getStatusResponseMessageConverter() {
		return new StatusResponseMessageConverter();
	}

	/**
	 * @return the status topic
	 */
	@Bean(name="statusTopicDestination")
	public Destination getStatusTopicDestination() {
		final String statusTopicName = environment.getProperty("grobot.status.topic");
		return new ActiveMQTopic(statusTopicName);
	}

	/**
	 * @return a jms template with a default destination set to the status topic
	 */
	@Bean(name="statusTopicJmsTemplate")
	public JmsTemplate getStatusTopicJmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(this.getJmsConnectionFactory());
		jmsTemplate.setDefaultDestination(this.getStatusTopicDestination());
		return jmsTemplate;
	}

	/**
	 * @return the status queue
	 */
	@Bean(name="statusQueueDestination")
	public Destination getStatusQueueDestination() {
		final String queueName = environment.getProperty("grobot.status.queue");
		return new ActiveMQQueue(queueName);
	}
	
	/**
	 * @return a jms template with a default destination set to the status queue
	 */
	@Bean(name="statusQueueJmsTemplate")
	public JmsTemplate getStatusQueueJmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(this.getJmsConnectionFactory());
		jmsTemplate.setDefaultDestination(this.getStatusQueueDestination());
		jmsTemplate.setMessageConverter(this.getStatusResponseMessageConverter());
		return jmsTemplate;
	}
}
