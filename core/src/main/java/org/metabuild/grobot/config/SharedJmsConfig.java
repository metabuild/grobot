package org.metabuild.grobot.config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.metabuild.grobot.mq.PingResponseMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Bean(name="grobotHostsQueue")
	public ActiveMQQueue getDestination() {
		return new ActiveMQQueue("grobot.hosts");
	}
	
	@Bean(name="pingResponseMessageConverter")
	public MessageConverter getPingResponseMessageConverter() {
		return new PingResponseMessageConverter();
	}
	
	@Bean(name="jmsTemplate")
	public JmsTemplate getJmsTemplate(ConnectionFactory connectionFactory, Destination grobotHostsQueue, MessageConverter messageConverter) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setDefaultDestination(grobotHostsQueue);
		jmsTemplate.setMessageConverter(messageConverter);
		return jmsTemplate;
	}
}
