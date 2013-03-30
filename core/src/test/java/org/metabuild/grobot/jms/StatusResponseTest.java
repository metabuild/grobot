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
package org.metabuild.grobot.jms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.junit.Test;
import org.metabuild.grobot.common.jms.StatusResponse;

public class StatusResponseTest {

	@Test
	public void testNonNullParams() {
		StatusResponse statusResponse = new StatusResponse("foo.bar.baz", System.getProperties(), new Properties());
		assertEquals("foo.bar.baz", statusResponse.getHostname());
		assertFalse(statusResponse.getSystemProperties().isEmpty());
		assertTrue(statusResponse.getOtherProperties().isEmpty());
	}
	
	@Test
	public void testSerialization()
		throws IOException, ClassNotFoundException {

		// construct actual object
		StatusResponse original = new StatusResponse("foo.bar.baz", System.getProperties(), new Properties());
		original.getOtherProperties().put("foo", "bar");
		
		// serialize
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(original);
		oos.close();
		
		//deserialize
		byte[] pickled = out.toByteArray();
		InputStream in = new ByteArrayInputStream(pickled);
		ObjectInputStream ois = new ObjectInputStream(in);
		StatusResponse copy = (StatusResponse) ois.readObject();
		
		// test the result
		assertEquals(original.getHostname(), copy.getHostname());
		assertEquals(original.getOtherProperties(), copy.getOtherProperties());
		assertEquals("bar", copy.getOtherProperties().get("foo"));

	}	

}
