package org.metabuild.grobot.mq;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@ComponentScan(basePackages = {"org.metabuild.grobot.mq"})
@PropertySource("file:${user.home}/.grobot/grobot.properties")
public class HeartbeatConfig {

	@Bean(name="activeMqUri")
	public String getActiveMqUri() {
		return "tcp://localhost:61616";
	}
	
	@Bean(name="jmsConnectionFactory")
	public ConnectionFactory getJmsConnectionFactory(String activeMqUri) {
	    final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
	    factory.setBrokerURL(activeMqUri);
	    return factory;
	}
	
	@Bean(name="destination")
	public ActiveMQQueue getDestination() {
		return new ActiveMQQueue("grobot.hosts");
	}
	
	@Bean(name="jmsTemplate")
	public JmsTemplate getJmsTemplate(ConnectionFactory connectionFactory, Destination destination) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setDefaultDestination(destination);
		return jmsTemplate;
	}
	
	@Bean(name="heartbeatProducer")
	public HeartbeatProducer getHeartbeatProducer(JmsTemplate jmsTemplate) {
		HeartbeatProducer producer = new HeartbeatProducer();
		producer.setJmsTemplate(jmsTemplate);
		return producer;
	}

	// TODO: figure out hot to register the message listener with the container
	@Bean(name="heartbeatListner")
	public HeartbeatListener getHeartbeatListener(JmsTemplate jmsTemplate) {
		HeartbeatListener listener = new HeartbeatListener();
		return listener;
	}

}
