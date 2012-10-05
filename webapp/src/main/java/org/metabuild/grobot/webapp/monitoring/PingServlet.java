package org.metabuild.grobot.webapp.monitoring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.metabuild.grobot.webapp.monitoring.PingServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used for monitoring of the web app's availability - it just prints "alive"
 * 
 * @author jburbridge
 * @since 6/23/2012
 */
public class PingServlet extends HttpServlet {

	private static Logger LOGGER = LoggerFactory.getLogger(PingServlet.class);
	private static final long serialVersionUID = -5773966776067572000L;
	private static final String ALIVE = "ALIVE";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.trace("{}: ping request received", PingServlet.class.getName());
		response.setContentType("text/plain");
		response.getWriter().println(ALIVE);
	}

}
