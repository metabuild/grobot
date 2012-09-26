package server



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TargetNode)
class TargetNodeTests {

    void testValid() {
		def validNode = new TargetNode(hostname: 'localhost', active: true).save()
		Assert.assertFalse(validNode.hasErrors())
    }
	
	void testInvalid() {
		try {
			def invalidNode = new TargetNode().save(failOnError: true)
			fail('Should have thrown a ValidationException')
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(),grails.validation.ValidationException.class)
		}
	}
}
