package org.metabuild.grobot.server.init;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class InitializerServlet
 * 
 * @author jburbridge
 * @since 6/27/2012
 */
public class InitializerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		Logger logger = LoggerFactory.getLogger(InitializerServlet.class);
		logger.info("========== INITIALIZING ROOT WEBAPP ==============");
	}
}