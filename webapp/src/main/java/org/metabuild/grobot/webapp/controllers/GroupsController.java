/*
 * Copyright 2013 Metabuild Software, LLC. (http://www.metabuild.org)
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

import org.metabuild.grobot.common.domain.TargetGroup;
import org.metabuild.grobot.server.repository.TargetGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jburbridge
 * @since 03/11/2013
 */
@Controller
@RequestMapping("/groups")
public class GroupsController extends AbstractBaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GroupsController.class);
	private static final String GROUPS_LIST_VIEW = "groups/list";
	private static final String GROUPS_DETAIL_VIEW = "groups/details";
	private static final String GROUPS_CREATE_VIEW = "groups/create";
	private static final String GROUPS_UPDATE_VIEW = "groups/update";

	@Autowired
	private TargetGroupRepository targetGroupRepository;
	
	/**
	 * Display the list of groups
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel, Pageable pageable) {
		
		final Page<TargetGroup> page = targetGroupRepository.findAll(pageable);
		uiModel.addAttribute("page", page);
		addSelectedMenuItem(uiModel);
		
		return GROUPS_LIST_VIEW;
	}
	
	/**
	 * the details of a target
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String details(@PathVariable("id") String id, Model uiModel) {
		
		uiModel.addAttribute("group", targetGroupRepository.findById(id));
		addSelectedMenuItem(uiModel);
		
		return GROUPS_DETAIL_VIEW;
	}
	
	/**
	 * the create form
	 */
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String createForm(Model uiModel) {
		
		return GROUPS_CREATE_VIEW;
	}

	/**
	 * creates a new record and presents the detail view
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String create(Model uiModel) {
		
		addSelectedMenuItem(uiModel);
		
		return GROUPS_DETAIL_VIEW;
	}

	/**
	 * the update form
	 */
	@RequestMapping(value="/form/{id}", method=RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model uiModel) {
		
		uiModel.addAttribute("group", targetGroupRepository.findById(id));
		addSelectedMenuItem(uiModel);
		
		return GROUPS_UPDATE_VIEW;
	}
	
	/**
	 * updates and presents the detail view
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String update(@PathVariable("id") String id, Model uiModel) {
		
		TargetGroup group = targetGroupRepository.findById(id);
		uiModel.addAttribute("group", group);
		addSelectedMenuItem(uiModel);
		
		return GROUPS_DETAIL_VIEW;
	}

	
	@Override
	public NavMenuItems getSelectedNavMenuItem() {
		return NavMenuItems.GROUPS;
	}
}
