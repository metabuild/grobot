package org.metabuild.grobot.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.metabuild.grobot.client.config.ClientConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
public class GrobotClientRunner {

	private static final ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		printHeader();
		final GrobotClient client = new GrobotClient(getClientName(), context);
		client.run();
	}
	
	/**
	 * Look for the client name in properties first and set it to the hostname if it hasn't 
	 * been set
	 * 
	 * @return the client name
	 */
	protected static String getClientName() {
		String hostname;
		try {
			hostname = System.getProperty("hostname") != null ? System.getProperty("hostname") : InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			hostname = "Unknown Host";
		}
		return hostname;
	}
	
	private static void printHeader() {
		System.out.println("-----------------------------------------------------------------------\n");
		System.out.println("       __|   _ \\    _ \\    _ )    _ \\   __ __| ");
		System.out.println("      (_ |     /   (   |   _ \\   (   |     |   ");
		System.out.println("     \\___|  _|_\\  \\___/   ___/  \\___/     _| ");
		System.out.println("                                  version: 0.1  ");
		System.out.println("-----------------------------------------------------------------------");
	}
}
