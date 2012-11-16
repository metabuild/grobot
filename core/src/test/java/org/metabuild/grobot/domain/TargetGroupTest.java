package org.metabuild.grobot.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
public class TargetGroupTest {

	@Test
	public void test() {
		TargetGroup parentGroup = new TargetGroup("group1");
		assertNotNull(parentGroup);
		assertEquals("group1", parentGroup.getName());

		TargetGroup childGroup = new TargetGroup("group2");
		assertEquals(1,childGroup.getTargets().size());
		
		TargetGroup grandChild = new TargetGroup("group3");
		assertEquals(1,grandChild.getTargets().size());

		childGroup.addTargets(grandChild.getTargets());
		parentGroup.addTargets(childGroup.getTargets());
		List<Targetable> targetables = parentGroup.getTargets();
		assertEquals(3, targetables.size());
	}

}
