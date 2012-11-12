package org.metabuild.grobot.webapp.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.metabuild.grobot.webapp.AbstractSpringEnabledTest;
import org.springframework.ui.ExtendedModelMap;

/**
 * @author jburbridge
 * @since 11/03/2012
 */
public class ConfigurationControllerTest extends AbstractSpringEnabledTest {

	@Test
	public void testIndex() {
		
		ConfigurationController controller = new ConfigurationController();
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.getIndex(uiModel);
		
		assertNotNull(result);
		assertEquals("config", result);
	}
}
