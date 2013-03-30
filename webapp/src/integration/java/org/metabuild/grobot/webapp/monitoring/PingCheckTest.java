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
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.metabuild.grobot.webapp.UrlBuilder;

/**
 * Verifies that ping.jsp is available and responding with ALIVE
 * 
 * @author jburbridge
 * @since 6/25/2012
 */
public class PingCheckTest {

	private static final String CHECK_VALUE = "ALIVE";
	private static final String PING_PATH = "/ping";
	
	private static DefaultHttpClient httpClient;
	
	@Test
	public void testPing() throws IOException {
		
		String url = UrlBuilder.getString(PING_PATH);
		StringBuilder resultString = new StringBuilder();
		httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url.toString());
		HttpResponse httpResponse = httpClient.execute(httpGet);
		BufferedReader in;
		
		try {
			HttpEntity entity = httpResponse.getEntity();
			in = new BufferedReader(new InputStreamReader(entity.getContent()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				resultString.append(inputLine);
			}
			EntityUtils.consume(entity);
			in.close();
		} finally {
			httpGet.releaseConnection();
		}
		assertEquals(200,httpResponse.getStatusLine().getStatusCode());
		assertTrue("Expected ALIVE but got a different result from PingCheck", 
				resultString.toString().indexOf(CHECK_VALUE) >= 0);
	}
}