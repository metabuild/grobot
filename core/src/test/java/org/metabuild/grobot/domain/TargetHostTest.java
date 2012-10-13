/**
 * 
 */
package org.metabuild.grobot.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.metabuild.grobot.mq.PingResponse;

/**
 * @author jburbridge
 * @since 10/13/2012
 */
public class TargetHostTest {

	/**
	 * Test method for {@link org.metabuild.grobot.domain.TargetHost#TargetHost(java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	public void testTargetHostStringStringBoolean() {
		TargetHost targetHost = new TargetHost("foo","bar", true);
		assertEquals("foo", targetHost.getName());
		assertEquals("bar", targetHost.getAddress());
		assertTrue(targetHost.isActive());
	}

	/**
	 * Test method for {@link org.metabuild.grobot.domain.TargetHost#TargetHost(org.metabuild.grobot.mq.PingResponse)}.
	 */
	@Test
	public void testTargetHostPingResponse() {
		PingResponse pingResponse = new PingResponse();
		TargetHost targetHost = new TargetHost(pingResponse);
		assertEquals(pingResponse.getHostname(),targetHost.getName());
		assertEquals(pingResponse.getSystemProperties(),targetHost.getSystemProperties());
		assertEquals(pingResponse.getOtherProperties(),targetHost.getOtherProperties());
		assertTrue(targetHost.isActive());
	}

	/**
	 * Test method for {@link org.metabuild.grobot.domain.TargetHost#isActive()}.
	 */
	@Test
	public void testIsActive() {
		TargetHost activeHost = new TargetHost("foo","bar", true);
		assertTrue(activeHost.isActive());
		TargetHost innactiveHost = new TargetHost("foo","bar", false);
		assertFalse(innactiveHost.isActive());
	}

}
