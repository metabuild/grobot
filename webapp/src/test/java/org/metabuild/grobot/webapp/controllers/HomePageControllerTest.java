/**
 * 
 */
package org.metabuild.grobot.webapp.controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;

/**
 * @author jburbridge
 * @since 10/24/2012
 */
public class HomePageControllerTest extends BaseControllerTest {

	@Test
	public void testIndex() {
		
		HomePageController controller = new HomePageController();
		ExtendedModelMap uiModel = new ExtendedModelMap();
		String result = controller.getIndex(uiModel);
		
		assertNotNull(result);
		assertEquals("index", result);
	}

}
