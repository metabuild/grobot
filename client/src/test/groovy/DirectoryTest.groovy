import static org.junit.Assert.*;
import org.junit.Test

class DirectoryTest {

	@Test
	public void testDirectories() {
		def foo = []
		foo << new File('c:/projects')
		foo << new File('c:/projects/grobot')
		def directories = [foo, foo, foo]
		directories.flatten()
		println "Directories: ${directories.flatten()}"
	}
}
