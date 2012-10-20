package org.metabuild.grobot.webapp.controllers;

import org.metabuild.grobot.domain.TargetHostCache;
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
	private TargetHostCache targetHostCache;
	
	public TargetListController() {
		
	}
	
	public TargetListController(TargetHostCache targetCacheImpl) {
		this.targetHostCache = targetCacheImpl;
	}
	
	@RequestMapping("/listTargets")
	public ModelAndView getHomePage(Model model) {
        return new ModelAndView("listTargets", "targetCache", targetHostCache.size());
	}
}
