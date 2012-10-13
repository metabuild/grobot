package org.metabuild.grobot.webapp.controllers;

import org.metabuild.grobot.domain.TargetCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jburbridge
 *
 */
@Controller
public class TargetListController {

	@Autowired(required=true)
	private TargetCache targetCache;
	
	public TargetListController() {
		
	}
	
	public TargetListController(TargetCache targetCache) {
		this.targetCache = targetCache;
	}
	
	@RequestMapping("/listTargets")
	public ModelAndView getHomePage(Model model) {
        return new ModelAndView("listTargets", "targetCache", targetCache.size());
	}
}
