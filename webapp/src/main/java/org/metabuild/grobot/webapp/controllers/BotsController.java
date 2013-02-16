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

import java.util.List;

import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.common.domain.TargetHostStatus;
import org.metabuild.grobot.server.service.TargetHostService;
import org.metabuild.grobot.server.status.StatusRequestProducer;
import org.metabuild.grobot.server.status.StatusRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
@RequestMapping("/bots")
@Controller
public class BotsController extends AbstractBaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BotsController.class);
	private static final String BOTS_LIST_VIEW = "bots/list";
	private static final String BOTS_DETAILS_VIEW = "bots/details";

	@Autowired
	private TargetHostService targetHostService;

	@Autowired
	private StatusRequestProducer producer;

	/**
	 * Display the list of targets
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel) {
		
		final List<TargetHost> targets = targetHostService.findAll();
		final long lastStatusRequest = StatusRequestService.getLastRunTimestamp();
		
		for (TargetHost target : targets) {
			if (target.getLastUpdatedStatus() == null || 
					lastStatusRequest > target.getLastUpdatedStatus().getMillis() + 30000) {
				LOGGER.debug("No activity for {} in the last 30 seconds", target);
				target.setStatus(TargetHostStatus.STOPPED);
			}
		}
		
		uiModel.addAttribute("targets", targets);
		addSelectedMenuItem(uiModel);
		
		return BOTS_LIST_VIEW;
	}
	
	/**
	 * Display the details of a target
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String details(@PathVariable("id") String id, Model uiModel) {
		
		final TargetHost target = targetHostService.find(id);
		uiModel.addAttribute("target", target);
		addSelectedMenuItem(uiModel);
		
		return BOTS_DETAILS_VIEW;
	}

	@Override
	public NavMenuItems getSelectedNavMenuItem() {
		return NavMenuItems.BOTS;
	}
}
