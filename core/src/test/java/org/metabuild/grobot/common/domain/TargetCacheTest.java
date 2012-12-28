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
package org.metabuild.grobot.common.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.common.domain.TargetHostCacheImpl;

/**
 * @author jburbridge
 *
 */
public class TargetCacheTest {

	/**
	 * Test method for {@link org.metabuild.grobot.common.domain.TargetHostCacheImpl#TargetCache()}.
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
