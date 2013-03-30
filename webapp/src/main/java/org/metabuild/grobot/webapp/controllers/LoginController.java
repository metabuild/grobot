/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.metabuild.grobot.webapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jburbridge
 * @since 3/10/2013
 */
@Controller
public class LoginController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	private static final String LOGIN_VIEW = "login/form";
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginForm(Model model) {
			LOGGER.debug("User not authenticated - presenting login form");
			return LOGIN_VIEW;
	}
	
	@RequestMapping(value="/loginfailed", method=RequestMethod.GET)
	public String getLoginFailed(Model model) {
			LOGGER.warn("Login failed");
			model.addAttribute("error", "true");
			return LOGIN_VIEW;
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String getLogoutForm(Model model) {
			LOGGER.debug("Logging out user");
			model.addAttribute("logout", "true");
			return LOGIN_VIEW;
	}
}
