package org.metabuild.grobot.webapp;

import org.junit.runner.RunWith;
import org.metabuild.grobot.server.config.TestAppConfig;
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
@ContextConfiguration(classes = TestAppConfig.class)
@ActiveProfiles(profiles = "default, test")
public abstract class AbstractSpringEnabledTest {

}
