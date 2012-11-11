package org.metabuild.grobot.webapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jburbridge
 * @since 11/03/2012
 */
@Controller
@RequestMapping("/config")
public class ConfigurationController extends AbstractBaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);
	private static final String CONFIG_PAGE_VIEW = "config";
	
	@RequestMapping(method=RequestMethod.GET)
	public String getIndex(Model uiModel) {
		LOGGER.debug("Displaying {} view", CONFIG_PAGE_VIEW);
		addSelectedMenuItem(uiModel);
		return CONFIG_PAGE_VIEW ;
	}

	@Override
	public NavMenuItems getSelectedNavMenuItem() {
		return NavMenuItems.CONFIG;
	}
}
