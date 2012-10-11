package org.metabuild.grobot.server.config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.metabuild.grobot.config.GrobotBaseMQConfig;
import org.metabuild.grobot.domain.TargetCache;
import org.metabuild.grobot.server.mq.HeartbeatListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractJmsListeningContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

@Import(GrobotBaseMQConfig.class)
@Configuration
public class HeartbeatConsumerConfig {

	@Bean(name="targetCache")
	public TargetCache getTargetCache() {
		return new TargetCache();
	}
	
	@Bean(name="heartbeatListner")
	public HeartbeatListener getHeartbeatListener(JmsTemplate jmsTemplate) {
		return new HeartbeatListener();
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
	public MessageListenerAdapter messageListenerAdapter(HeartbeatListener heartbeatListner) {
	    final MessageListenerAdapter adapter = new MessageListenerAdapter(heartbeatListner);
	    adapter.setDefaultListenerMethod("onMessage");
	    return adapter;
	}
}
