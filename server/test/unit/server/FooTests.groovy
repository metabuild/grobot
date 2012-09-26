package server

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*

import org.codehaus.groovy.grails.commons.GrailsApplication;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class FooTests {

	@Test
    void testSomething() {
		println "base.dir: ${System.properties['base.dir']}"
        println "user.home: ${System.properties['user.home']}"
        println "grobot.tasks.directory: ${System.properties['grobot.tasks.directory']}"
        println "grobot.tasks.directory: ${grailsApplication.config.grobot.tasks.directory}"
    }
}
