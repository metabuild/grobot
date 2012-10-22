package examples;

// prints the local time
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

def logger = LoggerFactory.getLogger("CatchingException");

try {
	throw new RuntimeException("Our custom exception message!")
} catch (Exception e) {
	logger.error("We caught the ${e.getClass()} and here's the message: ${e.getMessage()}")
}