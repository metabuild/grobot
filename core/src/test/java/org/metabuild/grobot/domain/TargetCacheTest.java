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
	 * Test method for {@link org.metabuild.grobot.domain.TargetCache#TargetCache()}.
	 */
	@Test
	public void testTargetCache() {
		// cache starts off empty
		TargetCache targetCache = new TargetCache();
		assertEquals(0,targetCache.size());
		// add a new host and check it
		targetCache.put("host", new TargetHost("foo", null, false));
		assertEquals(1,targetCache.size());
		assertEquals("foo", ((TargetHost) targetCache.get("host")).getName());
		// add a new group and check it
		targetCache.put("group", new TargetGroup("bar"));
		assertEquals(2,targetCache.size());
		assertEquals("bar", ((TargetGroup) targetCache.get("group")).getName());
	}
}
