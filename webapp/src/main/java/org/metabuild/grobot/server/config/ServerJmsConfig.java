package org.metabuild.grobot.server.config;

import java.net.UnknownHostException;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.metabuild.grobot.config.SharedJmsConfig;
import org.metabuild.grobot.domain.TargetHostCache;
import org.metabuild.grobot.server.mq.StatusRequestProducerImpl;
import org.metabuild.grobot.server.mq.StatusRequestProducer;
import org.metabuild.grobot.server.mq.StatusResponseListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractJmsListeningContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

/**
 * Server JMS Configuration
 * 
 * @author jburbridge
 * @since 10/13/2012
 */
@Configuration
@Import({SharedJmsConfig.class, DefaultAppConfig.class})
@PropertySource("classpath:grobot.properties")
public class ServerJmsConfig {

	/**
	 * Sends JMS status requests to the grobot.status.topic destination
	 * 
	 * @param statusTopicJmsTemplate
	 * @return the StatusRequestProducer
	 * @throws UnknownHostException
	 */
	@Autowired(required=true)
	@Bean(name="statusRequestProducer")
	public StatusRequestProducer getStatusRequestProducer(
			@Qualifier(value="statusTopicJmsTemplate") JmsTemplate statusTopicJmsTemplate) throws UnknownHostException {
		final StatusRequestProducer statusRequestProducer = new StatusRequestProducerImpl();
		statusRequestProducer.setJmsTemplate(statusTopicJmsTemplate);
		return statusRequestProducer;
	}

	/**
	 * Listens for the JMS status responses on the grobot.status.queue destination
	 * @param targetHostCache
	 * @return the status response listener
	 */
	@Autowired(required=true)
	@Bean(name="statusResponseListner")
	public StatusResponseListener getStatusResponseListener(TargetHostCache targetHostCache) {
		return new StatusResponseListener(targetHostCache);
	}

	/**
	 * @param statusResponseListner
	 * @return the MessageListnerAdapter
	 */
	@Bean(name="statusResponseListenerAdapter")
	public MessageListenerAdapter messageListenerAdapter(StatusResponseListener statusResponseListner) {
		final MessageListenerAdapter adapter = new MessageListenerAdapter(statusResponseListner);
		adapter.setDefaultListenerMethod("onMessage");
		return adapter;
	}

	/**
	 * @param jmsConnectionFactory
	 * @param statusQueueDestination
	 * @param statusResponseListenerAdapter
	 * @return the status topic Container
	 */
	@Autowired(required=true)
	@Bean(name="statusQueueJmsContainer")
	public AbstractJmsListeningContainer getStatusTopicContainer(ConnectionFactory jmsConnectionFactory, 
			@Qualifier(value="statusQueueDestination") Destination statusQueueDestination, 
			@Qualifier(value="statusResponseListenerAdapter") MessageListenerAdapter statusResponseListenerAdapter) {
		final DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(jmsConnectionFactory);
		container.setDestination(statusQueueDestination);
		container.setSessionTransacted(false);
		container.setConcurrentConsumers(5);
		container.setMessageListener(statusResponseListenerAdapter);
		return container;
	}
}
