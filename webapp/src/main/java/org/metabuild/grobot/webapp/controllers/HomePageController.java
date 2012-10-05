package org.metabuild.grobot.webapp.controllers;

import org.metabuild.grobot.webapp.domain.GreetingMessage;
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
public class HomePageController {

	@Autowired(required=true)
	private GreetingMessage greetingMessage;
	
	public HomePageController() {
		
	}
	
	public HomePageController(GreetingMessage greetingMessage) {
		this.greetingMessage = greetingMessage;
	}
	
	@RequestMapping("/index")
	public ModelAndView getHomePage(Model model) {
        return new ModelAndView("index", "hello", greetingMessage);
	}
	
	public void setGreetingMessage(GreetingMessage greetingMessage) {
		this.greetingMessage = greetingMessage;
	}
}
