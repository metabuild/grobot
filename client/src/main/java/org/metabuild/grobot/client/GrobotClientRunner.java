/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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
		return context.getBean("clientName", String.class);
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
