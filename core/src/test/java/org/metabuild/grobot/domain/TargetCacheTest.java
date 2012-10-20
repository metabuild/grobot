/**
 * 
 */
package org.metabuild.grobot.domain;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author jburbridge
 *
 */
public class TargetCacheTest {

	/**
	 * Test method for {@link org.metabuild.grobot.domain.TargetCacheImpl#TargetCache()}.
	 */
	@Test
	public void testTargetCache() {
		// cache starts off empty
		TargetCacheImpl targetCacheImpl = new TargetCacheImpl();
		assertEquals(0,targetCacheImpl.size());
		// add a new host and check it
		targetCacheImpl.put("host1", new TargetHost("foo", null, false));
		assertEquals(1,targetCacheImpl.size());
		assertEquals("foo", ((TargetHost) targetCacheImpl.get("host1")).getName());
		// add a new group and check it
		targetCacheImpl.put("host2", new TargetHost("bar", null, false));
		assertEquals(2,targetCacheImpl.size());
		assertEquals("bar", ((TargetHost) targetCacheImpl.get("host2")).getName());
	}
}
