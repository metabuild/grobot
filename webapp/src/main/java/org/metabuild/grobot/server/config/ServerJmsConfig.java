package org.metabuild.grobot.server.config;

import java.net.UnknownHostException;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import org.metabuild.grobot.config.SharedJmsConfig;
import org.metabuild.grobot.server.mq.PingRequestProducerImpl;
import org.metabuild.grobot.server.mq.PingRequestProducer;
import org.metabuild.grobot.server.mq.PingResponseListener;
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

@Configuration
@Import({SharedJmsConfig.class, AppConfig.class})
@PropertySource("file:${user.home}/.grobot/grobot.properties")
public class ServerJmsConfig {

	@Autowired(required=true)
	@Qualifier(value="jmsTemplate")
	@Bean(name="pingResponseListner")
	public PingResponseListener getPingResponseListener(JmsTemplate jmsTemplate) {
		PingResponseListener pingResponseListener = new PingResponseListener();
		pingResponseListener.setJmsTemplate(jmsTemplate);
		return pingResponseListener;
	}

	@Autowired(required=true)
	@Qualifier(value="pingTopicDestination")
	@Bean(name="jmsContainer")
	public AbstractJmsListeningContainer getPingTopicContainer(ConnectionFactory jmsConnectionFactory, Destination destination, 
			MessageListenerAdapter jmsListenerAdapter) {
		final DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(jmsConnectionFactory);
		container.setDestination(destination);
		container.setSessionTransacted(false);
		container.setConcurrentConsumers(5);
		container.setMessageListener(jmsListenerAdapter);
		return container;
	}
	 
	@Bean(name="jmsListenerAdapter")
	public MessageListenerAdapter messageListenerAdapter(PingResponseListener pingResponseListner) {
		final MessageListenerAdapter adapter = new MessageListenerAdapter(pingResponseListner);
		adapter.setDefaultListenerMethod("receivePingResponse");
		return adapter;
	}
	
	@Bean(name="pingRequestProducer")
	public PingRequestProducer getPingRequestProducer(JmsTemplate jmsTemplate) throws UnknownHostException {
		PingRequestProducer pingRequestProducer = new PingRequestProducerImpl();
		pingRequestProducer.setJmsTemplate(jmsTemplate);
		return pingRequestProducer;
	}
}
