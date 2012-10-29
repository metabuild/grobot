package org.metabuild.grobot.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
public class GrobotClientRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final GrobotClient client = new GrobotClient(getHostname());
		client.run();
	}
	
	public static String getHostname() {
		String hostname;
		try {
			hostname = System.getProperty("hostname") != null ? System.getProperty("hostname") : InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			hostname = "Unknown Host";
		}
		return hostname;
	}
}
