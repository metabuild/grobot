package org.metabuild.grobot.webapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jburbridge
 * @since 10/11/2012
 */
@Controller
@RequestMapping("/")
public class HomePageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);
	private static final String HOMEPAGE_INDEX_VIEW = "index";
	
	@RequestMapping(method=RequestMethod.GET)
	public String getIndex(Model model) {
		// TODO: implement authentication
		LOGGER.debug("User not authenticated - presenting login form");
		return HOMEPAGE_INDEX_VIEW;
	}
}
