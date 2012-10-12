/**
 * 
 */
package org.metabuild.grobot.client.mq;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jburbridge
 *
 */
public class HostnameResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HostnameResolver.class);
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
