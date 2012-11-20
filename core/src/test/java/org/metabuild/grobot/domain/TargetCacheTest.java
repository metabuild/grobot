/**
 * 
 */
package org.metabuild.grobot.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author jburbridge
 *
 */
public class TargetCacheTest {

	/**
	 * Test method for {@link org.metabuild.grobot.domain.TargetHostCacheImpl#TargetCache()}.
	 */
	@Test
	public void testTargetCache() {
		// cache starts off empty
		TargetHostCacheImpl targetHostCacheImpl = new TargetHostCacheImpl();
		assertEquals(0,targetHostCacheImpl.size());
		// add a new host and check it
		targetHostCacheImpl.put("host1", new TargetHost("foo", null, false));
		assertEquals(1,targetHostCacheImpl.size());
		assertEquals("foo", ((TargetHost) targetHostCacheImpl.get("host1")).getName());
		// add a new group and check it
		targetHostCacheImpl.put("host2", new TargetHost("bar", null, false));
		assertEquals(2,targetHostCacheImpl.size());
		assertEquals("bar", ((TargetHost) targetHostCacheImpl.get("host2")).getName());
	}
}
