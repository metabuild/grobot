package org.metabuild.grobot.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jburbridge
 *
 */
@Controller
public class HomePageController {

	@RequestMapping("/index")
	public ModelAndView getHomePage(Model model) {
        return new ModelAndView("index", "hello", "hello from the index page!");
	}
}
