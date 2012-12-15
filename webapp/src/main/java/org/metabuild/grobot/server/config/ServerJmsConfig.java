package org.metabuild.grobot.server.config;

import java.net.UnknownHostException;
import java.util.Date;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.metabuild.grobot.common.config.SharedJmsConfig;
import org.metabuild.grobot.server.registration.RegistrationRequestListener;
import org.metabuild.grobot.server.registration.RegistrationResponseProducer;
import org.metabuild.grobot.server.registration.RegistrationResponseProducerImpl;
import org.metabuild.grobot.server.registration.RegistrationService;
import org.metabuild.grobot.server.registration.RegistrationServiceImpl;
import org.metabuild.grobot.server.service.TargetHostService;
import org.metabuild.grobot.server.status.StatusRequestProducer;
import org.metabuild.grobot.server.status.StatusRequestProducerImpl;
import org.metabuild.grobot.server.status.StatusRequestService;
import org.metabuild.grobot.server.status.StatusResponseListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractJmsListeningContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Server JMS Configuration
 * 
 * @author jburbridge
 * @since 10/13/2012
 */
@Configuration
@Import({SharedJmsConfig.class, DefaultAppConfig.class})
@PropertySource("classpath:grobot-server.properties")
public class ServerJmsConfig {
	
	@Autowired
	Environment environment;
	
	
	/**
	 * Sends JMS registration responses to the grobot.registration.response.queue destination
	 * 
	 * @param jmsConnectionFactory
	 * @return the RegistrationResponseProducer
	 */
	@Autowired(required=true)
	@Bean(name="registrationResponseProducer")
	public RegistrationResponseProducer getRegistrationResponseProducer(ConnectionFactory jmsConnectionFactory,
			@Qualifier(value="registrationResponseQueueDestination") Destination registrationResponseQueueDestination) {
		final JmsTemplate jmsTemplate = new JmsTemplate(jmsConnectionFactory);
		jmsTemplate.setDefaultDestination(registrationResponseQueueDestination);
		final RegistrationResponseProducer reponseProducer = new RegistrationResponseProducerImpl();
		reponseProducer.setJmsTemplate(jmsTemplate);
		return reponseProducer;
	}
	
	/**
	 * @param targetHostService
	 * @param registrationResponseProducer
	 * @return the registrationService
	 */
	@Bean(name="registrationService")
	public RegistrationService getRegistrationService(TargetHostService targetHostService, 
			RegistrationResponseProducer registrationResponseProducer) {
		return new RegistrationServiceImpl(targetHostService, registrationResponseProducer);
	}
	
	/**
	 * Listens for the JMS registration requests on grobot.registration.queue 
	 * 
	 * @return the registration request listener
	 */
	@Bean(name="registrationRequestListner")
	public RegistrationRequestListener getRegistrationRequestListener(RegistrationService registrationService) {
		return new RegistrationRequestListener(registrationService);
	}

	/**
	 * @param registrationRequestListener
	 * @return the MessageListenerAdapter
	 */
	@Bean(name="registrationRequestListenerAdapter")
	public MessageListenerAdapter getRegistrationRequestListenerAdapter(RegistrationRequestListener registrationRequestListener) {
		final MessageListenerAdapter adapter = new MessageListenerAdapter(registrationRequestListener);
		adapter.setDefaultListenerMethod("onMessage");
		return adapter;
	}

	/**
	 * @param jmsConnectionFactory
	 * @param registrationRequestQueueDestination
	 * @param registrationRequestListenerAdapter
	 * @return the registration request container
	 */
	@Autowired(required=true)
	@Bean(name="registrationRequestQueueJmsContainer")
	public AbstractJmsListeningContainer getRegistrationRequestQueueContainer(ConnectionFactory jmsConnectionFactory, 
			@Qualifier(value="registrationRequestQueueDestination") Destination registrationRequestQueueDestination, 
			@Qualifier(value="registrationRequestListenerAdapter") MessageListenerAdapter registrationRequestListenerAdapter) {
		final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(jmsConnectionFactory);
		container.setDestination(registrationRequestQueueDestination);
		container.setSessionTransacted(false);
		container.setConcurrentConsumers(5);
		container.setMessageListener(registrationRequestListenerAdapter);
		return container;
	}
	
	/**
	 * @param statusRequestService
	 * @return the taskScheduler
	 */
	@Autowired(required=true)
	@Bean(name="statusRequestServiceScheduler")
	public TaskScheduler getTaskScheduler(StatusRequestService statusRequestService) {
		final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		final long fixedRate = Long.valueOf(environment.getProperty("grobot.status.ping.interval"));
		final Date startTime = new Date(System.currentTimeMillis() + fixedRate);
		scheduler.setPoolSize(1);
		scheduler.initialize();
		scheduler.scheduleAtFixedRate(statusRequestService, startTime, fixedRate);
		return scheduler;
	}
	
	/**
	 * @param statusRequestProducer
	 * @return the StatusRequestService
	 */
	@Bean(name="statusRequestSchedulerService")
	public StatusRequestService getStatusRequestSchedulerService(StatusRequestProducer statusRequestProducer) {
		return new StatusRequestService(statusRequestProducer);
	}
	
	/**
	 * Sends JMS status requests to the grobot.status.topic destination
	 * 
	 * @param statusTopicJmsTemplate
	 * @return the StatusRequestProducer
	 * @throws UnknownHostException
	 */
	@Autowired(required=true)
	@Bean(name="statusRequestProducer")
	public StatusRequestProducer getStatusRequestProducer(ConnectionFactory jmsConnectionFactory,
			@Qualifier(value="statusTopicDestination") Destination statusRequestDestination) throws UnknownHostException {
		final JmsTemplate jmsTemplate = new JmsTemplate(jmsConnectionFactory);
		jmsTemplate.setDefaultDestination(statusRequestDestination);
		final StatusRequestProducer statusRequestProducer = new StatusRequestProducerImpl();
		statusRequestProducer.setJmsTemplate(jmsTemplate);
		return statusRequestProducer;
	}

	/**
	 * Listens for the JMS status responses on the grobot.status.queue destination
	 * 
	 * @param targetHostService
	 * @return the status response listener
	 */
	@Autowired(required=true)
	@Bean(name="statusResponseListner")
	public StatusResponseListener getStatusResponseListener(TargetHostService targetHostService) {
		return new StatusResponseListener(targetHostService);
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
		final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(jmsConnectionFactory);
		container.setDestination(statusQueueDestination);
		container.setSessionTransacted(false);
		container.setConcurrentConsumers(5);
		container.setMessageListener(statusResponseListenerAdapter);
		return container;
	}
}
