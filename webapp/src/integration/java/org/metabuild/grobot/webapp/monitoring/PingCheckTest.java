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
package org.metabuild.grobot.webapp.monitoring;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Verifies that ping.jsp is available and responding with ALIVE
 * 
 * @author jburbridge
 * @since 6/25/2012
 */
public class PingCheckTest {

	private static final String CHECK_VALUE = "ALIVE";
	private static final String DEFAULT_HOSTNAME = "localhost";
	private static final String DEFAULT_PORT = "9090";
	private static final String PING_PATH = "/ping";
	private static URL url;
	
	@BeforeClass
	public static void setUp() throws MalformedURLException {
		
		String cargoHost = System.getProperty("cargo.hostname") != null ? 
				System.getProperty("cargo.hostname") : DEFAULT_HOSTNAME;

		String cargoPort = System.getProperty("cargo.port") != null ? 
				System.getProperty("cargo.port") : DEFAULT_PORT;
				
		url = new URL(new StringBuilder("http://").append(cargoHost)
				.append(":").append(cargoPort).append(PING_PATH).toString());
	}
	
	@Test
	public void test() throws IOException {
		
		StringBuilder resultString = new StringBuilder();
		HttpURLConnection connection  = (HttpURLConnection) url.openConnection();
		connection.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			resultString.append(inputLine);
		}
		in.close();
		connection.disconnect();
		
		assertTrue("Expected ALIVE but got a different result from PingCheck", 
				resultString.toString().indexOf(CHECK_VALUE) >= 0);
	}
}