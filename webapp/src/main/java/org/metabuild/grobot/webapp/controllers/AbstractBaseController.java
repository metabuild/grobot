package org.metabuild.grobot.webapp.controllers;

import org.springframework.ui.Model;

/**
 * Abstract controller that adds navigation context to it's subclasses
 * 
 * TODO: investigate whethere this would be better handled by an AOP injection
 * 
 * @author jburbridge
 * @since 11/5/2012
 */
public abstract class AbstractBaseController {

	/**
	 * Adds the selectedMenuItem to the Model
	 * 
	 * @param uiModel
	 */
	public void addSelectedMenuItem(Model uiModel) {
		uiModel.addAttribute("selectedMenuItem", getSelectedNavMenuItem().toString());
		uiModel.addAttribute("navMenuItems", NavMenuItems.values());
	}
	
	public abstract NavMenuItems getSelectedNavMenuItem();
}
