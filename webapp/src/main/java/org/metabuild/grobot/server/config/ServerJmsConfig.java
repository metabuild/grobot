package org.metabuild.grobot.server.config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageListener;

import org.metabuild.grobot.config.SharedJmsConfig;
import org.metabuild.grobot.server.mq.PingListener;
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

	@Bean(name="pingListner")
	public MessageListener getPingListener(JmsTemplate jmsTemplate) {
		return new PingListener();
	}

	@Bean(name="jmsContainer")
	public AbstractJmsListeningContainer jmsContainer(ConnectionFactory jmsConnectionFactory, Destination destination, 
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
	public MessageListenerAdapter messageListenerAdapter(PingListener pingListner) {
		final MessageListenerAdapter adapter = new MessageListenerAdapter(pingListner);
		adapter.setDefaultListenerMethod("onMessage");
		return adapter;
	}
}
