package org.metabuild.grobot.server.registration;

import javax.jms.Destination;

import org.metabuild.grobot.common.jms.RegistrationData;

/**
 * Manages the registration of target hosts
 * 
 * @author jburbridge
 * @since 11/17/2012
 */
public interface RegistrationService {

	public void handleRegistrationRequest(RegistrationData registrationDetails, Destination destination);
	
}
