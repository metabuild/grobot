package org.metabuild.grobot.client.config;

import java.net.UnknownHostException;

import org.metabuild.grobot.client.mq.PingResponseProducer;
import org.metabuild.grobot.client.mq.DefaultPingResponseProducer;
import org.metabuild.grobot.config.SharedJmsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;

@Import(SharedJmsConfig.class)
@Configuration
public class ClientJmsConfig {

	@Bean(name="pingProducer")
	public PingResponseProducer getPingProducer(JmsTemplate jmsTemplate) throws UnknownHostException {
		PingResponseProducer producer = new DefaultPingResponseProducer();
		producer.setJmsTemplate(jmsTemplate);
		return producer;
	}
}
