package org.metabuild.grobot.webapp.controllers;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.metabuild.grobot.server.config.ControllerTestConfig;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base class that imports the required config for our controller tests
 * 
 * @author jburbridge
 * @since 10/23/2012
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ControllerTestConfig.class)
@ActiveProfiles("test")
public class BaseControllerTest {

	@Ignore("Apparently we need a NOOP test for Gradle")
	@Test
	public void fakeTest() {
		fail("Gradle tests require that this class have a runnable method, even if it's ignored!");
	}
}
