package org.metabuild.grobot.webapp.init;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.metabuild.grobot.server.config.HeartbeatConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Servlet implementation class InitializerServlet
 * 
 * @author jburbridge
 * @since 6/27/2012
 */
public class InitializerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(InitializerServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		LOGGER.info("<<<<<<<<<<  Initializing Grobot Server  >>>>>>>>>>");
		LOGGER.info("Initializing spring jms support...");
		ApplicationContext appContext = new AnnotationConfigApplicationContext(HeartbeatConsumerConfig.class); 
		LOGGER.info("Listening to {}", appContext.getBean("grobotHostsQueue"));

	}
}
