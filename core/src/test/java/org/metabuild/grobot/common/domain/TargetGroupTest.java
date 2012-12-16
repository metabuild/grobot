package org.metabuild.grobot.common.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.metabuild.grobot.common.domain.TargetGroup;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
public class TargetGroupTest {

	@Test
	public void test() {
		TargetGroup group = new TargetGroup("group1");
		assertNotNull(group);
		assertEquals("group1", group.getName());
		assertTrue(group.isActive());
		assertEquals(0, group.getTargetHosts().size());
	}

}
