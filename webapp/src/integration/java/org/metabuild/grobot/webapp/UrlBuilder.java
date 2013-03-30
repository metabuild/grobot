package org.metabuild.grobot.webapp;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Given a relative path name, build a complete URL based on pre-set system properties or
 * use some default values
 * 
 * @author jburbrid
 *
 */
public class UrlBuilder {

	private static final String DEFAULT_PROTOCOL = "http";
	private static final String DEFAULT_HOSTNAME = "localhost";
	private static final String DEFAULT_CONTEXT = "/";
	private static final int DEFAULT_PORT = 9090;
	
	private static final String PROP_CARGO_PROTOCOL = "cargo.protocol";
	private static final String PROP_CARGO_HOSTNAME = "cargo.hostname";
	private static final String PROP_CARGO_CONTEXT = "cargo.context";
	private static final String PROP_CARGO_PORT = "cargo.port";

	/**
	 * Given a path parameter, build the complete URL based on cargo properties
	 * 
	 * @param path
	 * @return the complete url
	 */
	public static String fillInPath(String path) {
		return new StringBuilder()
			.append(System.getProperty(PROP_CARGO_PROTOCOL) != null ? 
				System.getProperty(PROP_CARGO_PROTOCOL) : DEFAULT_PROTOCOL)
			.append("://")
			.append(System.getProperty(PROP_CARGO_HOSTNAME) != null ? 
				System.getProperty(PROP_CARGO_HOSTNAME) : DEFAULT_HOSTNAME)
			.append(":")
			.append(System.getProperty(PROP_CARGO_PORT) != null ? 
				System.getProperty(PROP_CARGO_PORT) : DEFAULT_PORT)
			.append(System.getProperty(PROP_CARGO_CONTEXT) != null ? 
				System.getProperty(PROP_CARGO_CONTEXT) : DEFAULT_CONTEXT)
			.append(path).toString();
	}
	
	public static String getString(String path) throws MalformedURLException {
		return getURL(path).toString();
	}
	
	public static URL getURL(String path) throws MalformedURLException {
		return new URL(fillInPath(path));
	}
}
