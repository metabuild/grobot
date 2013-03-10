/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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
 * @since 10/11/2012
 */
@Controller
@RequestMapping("/")
public class HomePageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);
	private static final String HOMEPAGE_INDEX_VIEW = "bots/list";
	
	@RequestMapping(method=RequestMethod.GET)
	public String getIndex(Model model) {
		// TODO: need better homepage?
		return HOMEPAGE_INDEX_VIEW;
	}
}
