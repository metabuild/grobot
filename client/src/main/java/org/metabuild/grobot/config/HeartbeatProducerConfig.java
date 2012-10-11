package org.metabuild.grobot.config;

import org.metabuild.grobot.mq.HeartbeatProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;

@Import(GrobotBaseMQConfig.class)
@Configuration
public class HeartbeatProducerConfig {

	@Bean(name="heartbeatProducer")
	public HeartbeatProducer getHeartbeatProducer(JmsTemplate jmsTemplate) {
		HeartbeatProducer producer = new HeartbeatProducer();
		producer.setJmsTemplate(jmsTemplate);
		return producer;
	}
}
