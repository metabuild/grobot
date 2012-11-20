package org.metabuild.grobot.server.registration;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;

import org.metabuild.grobot.domain.TargetHost;

/**
 * Manages the registration of target hosts
 * 
 * @author jburbridge
 * @since 11/17/2012
 */
public interface RegistrationService {

	public void createUnregistered(TargetHost targetHost);

	public List<TargetHost> getPendingRegistrations();

	public void handleRegistrationRequest(Message message) throws JMSException;
	
}
