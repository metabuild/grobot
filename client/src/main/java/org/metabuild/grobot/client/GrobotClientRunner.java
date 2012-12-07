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
		final GrobotClient client = new GrobotClient(context, getClientName(), getAddress());
		client.run();
	}
	
	/**
	 * Look for the hostname in properties first in case it was overridden. otherwise use the default used 
	 * by the operating system. 
	 * 
	 * @return the hostname
	 */
	protected static String getClientName() {
		return (String) context.getBean("clientName", String.class);
	}
	
	/**
	 * @return the host address
	 */
	private static String getAddress() {
		String address = null;
		try {
			address = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.err.println("WARN: Grobot could not determine the host address.");
		}
		return address;
	}

	/*
	 * Who doesn't like ascii art?
	 */
	private static void printHeader() {
		System.out.println("-----------------------------------------------------------------------\n");
		System.out.println("       __|   _ \\    _ \\    _ )    _ \\   __ __| ");
		System.out.println("      (_ |     /   (   |   _ \\   (   |     |   ");
		System.out.println("     \\___|  _|_\\  \\___/   ___/  \\___/     _|           version: 0.1");
		System.out.println("                                                                       ");
		System.out.println("-----------------------------------------------------------------------");
	}
}
