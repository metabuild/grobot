/**
 * 
 */
package org.metabuild.grobot.jms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.metabuild.grobot.common.jms.RegistrationDetails;

/**
 * @author jburbridge
 *
 */
public class RegistrationMessageDetailsTest {

	/**
	 * Test method for {@link org.metabuild.grobot.common.jms.RegistrationDetails#RegistrationMessageDetails(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRegistrationMessageDetails() {
		RegistrationDetails registrationMessageDetails  = new RegistrationDetails("hostname", "address");
		assertEquals("hostname", registrationMessageDetails.getHostname());
		assertEquals("address", registrationMessageDetails.getAddress());
	}
}
