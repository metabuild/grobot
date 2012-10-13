package org.metabuild.grobot.client.config;

import java.net.UnknownHostException;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import org.metabuild.grobot.client.mq.PingRequestListener;
import org.metabuild.grobot.client.mq.PingResponseProducer;
import org.metabuild.grobot.client.mq.PingResponseProducerImpl;
import org.metabuild.grobot.config.SharedJmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractJmsListeningContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

@Import(SharedJmsConfig.class)
@Configuration
public class ClientJmsConfig {

	@Autowired(required=true)
	@Qualifier(value="jmsTmemplate")
	@Bean(name="pingRequestListner")
	public PingRequestListener getPingRequestListener(JmsTemplate jmsTemplate) {
		PingRequestListener pingRequestListener = new PingRequestListener();
		pingRequestListener.setJmsTemplate(jmsTemplate);
		return pingRequestListener;
	}

	@Autowired(required=true)
	@Qualifier(value="pingTopicDestination")
	@Bean(name="jmsContainer")
	public AbstractJmsListeningContainer getPingTopicContainer(ConnectionFactory jmsConnectionFactory, Destination destination, 
			MessageListenerAdapter jmsListenerAdapter) {
		final DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(jmsConnectionFactory); 
		container.setDestination(destination);
		container.setPubSubDomain(true);
		container.setSessionTransacted(false);
		container.setConcurrentConsumers(1);
		container.setMessageListener(jmsListenerAdapter);
		return container;
	}
	 
	@Bean(name="jmsListenerAdapter")
	public MessageListenerAdapter messageListenerAdapter(PingRequestListener pingRequestListner) {
		final MessageListenerAdapter adapter = new MessageListenerAdapter(pingRequestListner);
		adapter.setDefaultListenerMethod("receivePingRequest");
		return adapter;
	}

	@Bean(name="pingResponseProducer")
	public PingResponseProducer getPingResponseProducer(JmsTemplate jmsTemplate) throws UnknownHostException {
		PingResponseProducer producer = new PingResponseProducerImpl();
		producer.setJmsTemplate(jmsTemplate);
		return producer;
	}
}
