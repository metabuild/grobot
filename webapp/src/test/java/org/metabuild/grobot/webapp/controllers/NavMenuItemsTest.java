package org.metabuild.grobot.webapp.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author jburbridge
 * @since 11/11/2012
 */
public class NavMenuItemsTest {

	@Test
	public void testToString() {
		assertEquals("bots", NavMenuItems.BOTS.toString());
	}
	
	@Test
	public void testGetPath() {
		assertEquals("bots", NavMenuItems.BOTS.getPath());
	}
}
