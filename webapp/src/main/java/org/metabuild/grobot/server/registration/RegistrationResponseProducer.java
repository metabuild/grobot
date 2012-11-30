package org.metabuild.grobot.server.registration;

import org.springframework.jms.core.JmsTemplate;

/**
 * @author jburbridge
 * @since 11/17/2012
 */
public interface RegistrationResponseProducer {

	/**
	 * Send the registration response along with a reply-to destination 
	 * 
	 * @param replyToDestination 
	 */
	public void sendRegistrationResponse(String hostname);

	/**
	 * Allows for the jmsTemplate to be injected for unit testing
	 * @param jmsTemplate
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate);

}
