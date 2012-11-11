package org.metabuild.grobot.webapp.controllers;

import org.springframework.ui.Model;

/**
 * @author jburbridge
 * @since 11/5/2012
 */
public abstract class AbstractBaseController {

	public void addSelectedMenuItem(Model uiModel) {
		uiModel.addAttribute("selectedMenuItem", getSelectedNavMenuItem().toString());
		uiModel.addAttribute("navMenuItems", NavMenuItems.values());
	}
	
	public abstract NavMenuItems getSelectedNavMenuItem();
}
