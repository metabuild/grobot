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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

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
	private static final String GROUPS_FORM_VIEW = "groups/update";

	@Autowired
	private TargetGroupRepository targetGroupRepository;
	
	/**
	 * Display the list of groups
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel, Pageable pageable) {
		
		final Page<TargetGroup> page = targetGroupRepository.findAll(pageable);
		uiModel.addAttribute("page", page);
		
		return GROUPS_LIST_VIEW;
	}
	
	/**
	 * the details of a target
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String show(@PathVariable("id") String id, Model uiModel) {
		
		uiModel.addAttribute("group", targetGroupRepository.findById(id));
		
		return GROUPS_DETAIL_VIEW;
	}
	
	/**
	 * setup the create form
	 */
	@RequestMapping(method=RequestMethod.GET, params="form")
	public String createForm(Model uiModel) {
		TargetGroup group = new TargetGroup();
		uiModel.addAttribute("group", group);
		return GROUPS_FORM_VIEW;
	}

	/**
	 * creates a new record and presents the detail view
	 */
	@RequestMapping(method=RequestMethod.POST, params="form")
	public String create(@ModelAttribute("group") TargetGroup group, BindingResult result, Model uiModel) {
		group = targetGroupRepository.save(group);
		uiModel.addAttribute("group", group);
		return GROUPS_DETAIL_VIEW;
	}

	/**
	 * the update form
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET, params="form")
	public String updateForm(@PathVariable("id") String id, Model uiModel) {
		
		uiModel.addAttribute("group", targetGroupRepository.findById(id));
		return GROUPS_FORM_VIEW;
	}
	
	/**
	 * updates and presents the detail view
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.POST, params="form")
	public String update(TargetGroup group, BindingResult result, Model uiModel) {
		
		if (result.hasErrors()) {
			uiModel.addAttribute("errorMessage", result.getAllErrors());
		}
		group = targetGroupRepository.save(group);
		uiModel.addAttribute("group", group);
		
		return GROUPS_DETAIL_VIEW;
	}

	
	/**
	 * deletes and presents the list view
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.POST, params="delete")
	public String delete(@PathVariable("id") String id, Model uiModel) {
		
		TargetGroup group = targetGroupRepository.findById(id);
		uiModel.addAttribute("group", group);
		targetGroupRepository.delete(id);
		
		return GROUPS_LIST_VIEW;
	}

	
	@Override
	public NavMenuItems getSelectedNavMenuItem() {
		return NavMenuItems.GROUPS;
	}
}
