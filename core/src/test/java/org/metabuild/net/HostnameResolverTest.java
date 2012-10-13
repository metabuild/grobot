/**
 * 
 */
package org.metabuild.net;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.InetAddress;

import org.junit.Test;

/**
 * @author jburbridge
 *
 */
public class HostnameResolverTest {

	private final byte[] addressBytes = new byte[]{0,0,0,0};

	/**
	 * Test method for {@link org.metabuild.net.HostnameResolver#getHostname()}.
	 */
	@Test
	public void testGetHostname() {
		HostnameResolver resolver = new HostnameResolver(getMockInetAddress());
		assertEquals("my.fake.host", resolver.getHostname());
	}

	/**
	 * Test method for {@link org.metabuild.net.HostnameResolver#getAddress()}.
	 */
	@Test
	public void testGetAddress() {
		HostnameResolver resolver = new HostnameResolver(getMockInetAddress());
		assertEquals(addressBytes, resolver.getAddress());
	}
	
	private InetAddress getMockInetAddress() {
		InetAddress inetAddress = mock(InetAddress.class);
		when(inetAddress.getHostName()).thenReturn("my.fake.host");
		when(inetAddress.getAddress()).thenReturn(addressBytes);
		return inetAddress;
	}

}
