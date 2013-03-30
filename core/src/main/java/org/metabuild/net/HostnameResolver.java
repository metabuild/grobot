package org.metabuild.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Utility class to resolve hostnames and addresses. This is particularly useful when it's used as
 * an injected dependency because it allows us to swap this class for a mock during unit testing,
 * so that hostnames aren't retrieved from the host where the test is running on (as in the build server
 * for example).</p> 
 * 
 * <p><strong>NOTE: this method for resolving hostnames assumes that the system's administrators 
 * having given the host a unique name and not just let the host refer to itself as 
 * "localhost" or "ubuntu".</strong></p>
 * 
 * @author jburbridge
 * @since 10/11/2012
 */
public class HostnameResolver {

	private final InetAddress addr;
	
	/**
	 * Default constructor uses that uses InetAddress.getLocalHost()
	 * @throws UnknownHostException
	 */
	public HostnameResolver() throws UnknownHostException {
		this(InetAddress.getLocalHost());
	}
	
	/**
	 * Constructor for unit testing
	 * @param addr
	 */
	protected HostnameResolver(InetAddress addr) {
		this.addr = addr;
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return addr.getHostName();
	}
	
	/**
	 * @return the address as an array of bytes
	 */
	public byte[] getAddress() {
		return addr.getAddress();
	}
}
