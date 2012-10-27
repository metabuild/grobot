package org.metabuild.grobot.client.config;

import java.net.UnknownHostException;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.metabuild.grobot.client.mq.StatusRequestListener;
import org.metabuild.grobot.client.mq.StatusResponseProducer;
import org.metabuild.grobot.client.mq.StatusResponseProducerImpl;
import org.metabuild.grobot.config.SharedJmsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractJmsListeningContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

/**
 * Client JMS Configuration
 * 
 * @author jburbridge
 * @since 10/13/2012
 */
@Import(SharedJmsConfig.class)
@Configuration
public class ClientJmsConfig {

	@Autowired
	Environment environment;
	
	@Autowired(required=true)
	@Qualifier(value="statusResponseProducer")
	@Bean(name="statusRequestListner")
	public StatusRequestListener getStatusRequestListener(StatusResponseProducer statusResponseProducer) {
		StatusRequestListener statusRequestListener = new StatusRequestListener();
		statusRequestListener.setStatusResponseProducer(statusResponseProducer);
		return statusRequestListener;
	}
	
	@Bean(name="statusRequestJmsListenerAdapter")
	public MessageListenerAdapter messageListenerAdapter(StatusRequestListener statusRequestListner) {
		final MessageListenerAdapter adapter = new MessageListenerAdapter(statusRequestListner);
		adapter.setDefaultListenerMethod("onMessage");
		return adapter;
	}
		
	@Autowired(required=true)
	@Bean(name="statusTopicJmsContainer")
	public AbstractJmsListeningContainer getStatusTopicContainer(ConnectionFactory jmsConnectionFactory, 
			@Qualifier(value="statusTopicDestination") Destination statusTopicDestination, 
			@Qualifier(value="statusRequestJmsListenerAdapter") MessageListenerAdapter statusRequestJmsListenerAdapter) {
		final DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(jmsConnectionFactory); 
		container.setDestination(statusTopicDestination);
		container.setPubSubDomain(true);
		container.setSessionTransacted(false);
		container.setConcurrentConsumers(1);
		container.setMessageListener(statusRequestJmsListenerAdapter);
		return container;
	}
	 
	@Autowired(required=true)
	@Bean(name="statusResponseProducer")
	public StatusResponseProducer getStatusResponseProducer(
			@Qualifier(value="statusQueueJmsTemplate") JmsTemplate statusQueueJmsTemplate) throws UnknownHostException {
		StatusResponseProducer producer = new StatusResponseProducerImpl(environment.getProperty("hostname"));
		producer.setJmsTemplate(statusQueueJmsTemplate);
		return producer;
	}
}
