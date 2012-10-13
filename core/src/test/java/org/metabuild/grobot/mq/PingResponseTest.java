package org.metabuild.grobot.mq;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.junit.Test;

public class PingResponseTest {

	@Test
	public void testNullParams() {
		PingResponse pingResponse = new PingResponse("foo.bar.baz", System.getProperties(), new Properties());
		assertEquals("foo.bar.baz", pingResponse.getHostname());
		assertFalse(pingResponse.getSystemProperties().isEmpty());
		assertTrue(pingResponse.getOtherProperties().isEmpty());
	}
	
	@Test
	public void testSerialization()
		throws IOException, ClassNotFoundException {

		// construct actual object
		PingResponse original = new PingResponse("foo.bar.baz", System.getProperties(), new Properties());
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
		PingResponse copy = (PingResponse) ois.readObject();
		
		// test the result
		assertEquals(original.getHostname(), copy.getHostname());
		assertEquals(original.getOtherProperties(), copy.getOtherProperties());
		assertEquals("bar", copy.getOtherProperties().get("foo"));

	}	

}
