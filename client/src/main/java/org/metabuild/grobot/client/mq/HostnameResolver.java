/**
 * 
 */
package org.metabuild.grobot.client.mq;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author jburbridge
 * @since 10/11/2012
 */
public class HostnameResolver {

    private final InetAddress addr;
    
    public HostnameResolver() throws UnknownHostException {
    	this(InetAddress.getLocalHost());
    }
    
	protected HostnameResolver(InetAddress addr) {
		this.addr = addr;
	}

	public String getHostname() {
		String hostname = null;
		hostname = addr.getHostName();
		return hostname;
	}
	
	public byte[] getAddress() {
		byte[] ipAddr = null;
		ipAddr = addr.getAddress();
		return ipAddr;
	}
}
