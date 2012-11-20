package org.metabuild.grobot.client.registration;

import javax.jms.JMSException;

import org.springframework.jms.core.JmsTemplate;

/**
 * @author jburbridge
 * @since 10/11/2012
 */
public interface RegistrationRequestProducer {

	public void sendRegistrationRequest() throws JMSException;
	
	public void setJmsTemplate(JmsTemplate jsmTemplate);
}
