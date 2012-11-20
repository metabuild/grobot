package org.metabuild.grobot.client.config;

import java.net.UnknownHostException;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.metabuild.grobot.client.registration.RegistrationRequestProducer;
import org.metabuild.grobot.client.registration.RegistrationRequestProducerImpl;
import org.metabuild.grobot.client.status.StatusRequestListener;
import org.metabuild.grobot.client.status.StatusResponseProducer;
import org.metabuild.grobot.client.status.StatusResponseProducerImpl;
import org.metabuild.grobot.common.config.SharedJmsConfig;
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
	
	/**
	 * @return the registration request destination
	 */
	public Destination getRegistrationRequestQueueDestination() {
		final String queueName = environment.getProperty("grobot.registration.request.queue");
		return new ActiveMQQueue(queueName);
	}
	
	/**
	 * @param jmsConnectionFactory
	 * @return the registrationRequestProducer
	 */
	@Bean(name="registrationRequestProducer")
	public RegistrationRequestProducer getRegistrationRequestProducer(
			@Qualifier(value="jmsConnectionFactory") ConnectionFactory jmsConnectionFactory) {
		final JmsTemplate jmsTemplate = new JmsTemplate(jmsConnectionFactory);
		jmsTemplate.setDefaultDestination(getRegistrationRequestQueueDestination());
		final RegistrationRequestProducer registrationRequestProducer = new RegistrationRequestProducerImpl();
		registrationRequestProducer.setJmsTemplate(jmsTemplate);
		return registrationRequestProducer;
	}
	
	/**
	 * @return the registration response destination
	 */
	@Bean(name="registrationResponseQueueDestination")
	public Destination getRegistrationResponseQueueDestination() {
		final String queueName = environment.getProperty("grobot.registration.response.queue");
		return new ActiveMQQueue(queueName);
	}
	
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
			@Qualifier(value="statusQueueDestination") Destination statusQueueDestination,
			@Qualifier(value="jmsConnectionFactory") ConnectionFactory jmsConnectionFactory) throws UnknownHostException {
		final JmsTemplate jmsTemplate = new JmsTemplate(jmsConnectionFactory);
		jmsTemplate.setDefaultDestination(statusQueueDestination);
		StatusResponseProducer producer = new StatusResponseProducerImpl(environment.getProperty("hostname"));
		producer.setJmsTemplate(jmsTemplate);
		return producer;
	}
}
