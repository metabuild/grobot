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
	 * Test method for {@link org.metabuild.grobot.common.jms.RegistrationData#RegistrationData(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRegistrationData() {
		RegistrationData data  = new RegistrationData("key","hostname", "address");
		assertEquals("key", data.getKey());
		assertEquals("hostname", data.getHostname());
		assertEquals("address", data.getAddress());
	}
}
