/**
 * 
 */
package org.metabuild.grobot.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.common.jms.StatusResponse;

/**
 * @author jburbridge
 * @since 10/13/2012
 */
public class TargetHostTest {

	/**
	 * Test method for {@link org.metabuild.grobot.common.domain.TargetHost#TargetHost(java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	public void testTargetHostStringStringBoolean() {
		TargetHost targetHost = new TargetHost("foo","bar", true);
		assertEquals("foo", targetHost.getName());
		assertEquals("bar", targetHost.getAddress());
		assertTrue(targetHost.isActive());
	}

	/**
	 * Test method for {@link org.metabuild.grobot.common.domain.TargetHost#TargetHost(org.metabuild.grobot.common.jms.StatusResponse)}.
	 */
	@Test
	public void testTargetHostStatusResponse() {
		StatusResponse statusResponse = new StatusResponse();
		TargetHost targetHost = new TargetHost(statusResponse);
		assertEquals(statusResponse.getHostname(),targetHost.getName());
		assertEquals(statusResponse.getSystemProperties(),targetHost.getSystemProperties());
		assertEquals(statusResponse.getOtherProperties(),targetHost.getCustomProperties());
		assertTrue(targetHost.isActive());
	}

	/**
	 * Test method for {@link org.metabuild.grobot.common.domain.TargetHost#isActive()}.
	 */
	@Test
	public void testIsActive() {
		TargetHost activeHost = new TargetHost("foo","bar", true);
		assertTrue(activeHost.isActive());
		TargetHost innactiveHost = new TargetHost("foo","bar", false);
		assertFalse(innactiveHost.isActive());
	}

}
