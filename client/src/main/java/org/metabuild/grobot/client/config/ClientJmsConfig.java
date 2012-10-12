package org.metabuild.grobot.client.config;

import java.net.UnknownHostException;

import org.metabuild.grobot.client.mq.HeartbeatProducer;
import org.metabuild.grobot.client.mq.HeartbeatProducerImpl;
import org.metabuild.grobot.config.SharedJmsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;

@Import(SharedJmsConfig.class)
@Configuration
public class ClientJmsConfig {

	@Bean(name="heartbeatProducer")
	public HeartbeatProducer getHeartbeatProducer(JmsTemplate jmsTemplate) throws UnknownHostException {
		HeartbeatProducer producer = new HeartbeatProducerImpl();
		producer.setJmsTemplate(jmsTemplate);
		return producer;
	}
}
