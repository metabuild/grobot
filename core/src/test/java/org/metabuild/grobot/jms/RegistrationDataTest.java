/**
 * 
 */
package org.metabuild.grobot.jms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.metabuild.grobot.common.jms.RegistrationData;

/**
 * @author jburbridge
 *
 */
public class RegistrationDataTest {

	/**
	 * Test method for {@link org.metabuild.grobot.common.jms.RegistrationData#RegistrationMessageDetails(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRegistrationData() {
		RegistrationData registrationMessageDetails  = new RegistrationData("hostname", "address");
		assertEquals("hostname", registrationMessageDetails.getHostname());
		assertEquals("address", registrationMessageDetails.getAddress());
	}
}
