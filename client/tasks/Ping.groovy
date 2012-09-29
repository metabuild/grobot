import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

def logger = LoggerFactory.getLogger("CatchingException");

logger.warn("PING!")

return "Exiting with a nice message."
