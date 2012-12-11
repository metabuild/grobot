package org.metabuild.grobot.client.registration;

import javax.jms.JMSException;

import org.metabuild.grobot.common.jms.RegistrationData;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author jburbridge
 * @since 10/11/2012
 */
public interface RegistrationRequestProducer {

	public void setJmsTemplate(JmsTemplate jsmTemplate);

	public void sendRegistrationRequest(RegistrationData registrationData) throws JMSException;
}
