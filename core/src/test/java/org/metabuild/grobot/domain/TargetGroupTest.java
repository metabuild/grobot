/**
 * 
 */
package org.metabuild.grobot.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * @author jburbrid
 *
 */
public class TargetGroupTest {

	@Test
	public void test() {
		TargetGroup parent = new TargetGroup("group1");
		assertNotNull(parent);
		assertEquals("group1", parent.getName());
		
		TargetGroup child = new TargetGroup("group2");
		parent.getTargets().add(child);
		
		TargetHost host1 = new TargetHost("host1", "localhost", true);
		parent.getTargets().add(host1);
		
		List<Target> targets = parent.getTargets();
		assertEquals(3, targets.size());
	}

}
