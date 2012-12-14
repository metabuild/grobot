package org.metabuild.grobot.webapp.controllers;

import java.util.List;

import org.metabuild.grobot.scripts.groovy.GroovyScript;
import org.metabuild.grobot.scripts.groovy.GroovyScriptCache;
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
 * @since 10/21/2012
 */
@RequestMapping("/tasks")
@Controller
public class TaskController extends AbstractBaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private GroovyScriptCache taskCache;

	/**
	 * Display the list of tasks
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel) {
		
		final List<GroovyScript> tasks = taskCache.getAll();
		uiModel.addAttribute("scripts", tasks);
		addSelectedMenuItem(uiModel);
		
		return "tasks/list";
	}
	
	/**
	 * Display the details of a task
	 */
	@RequestMapping(value="/{hash}", method=RequestMethod.GET)
	public String details(@PathVariable("hash") String hash, Model uiModel) {
		
		final GroovyScript task = taskCache.get(hash);
		if (task != null) {
			uiModel.addAttribute("task", task);
		} else {
			LOGGER.warn("Couldn't find task with hash {} in task cache.", hash);
		}
		addSelectedMenuItem(uiModel);
		
		return "scripts/details";
	}
	
	@Override
	public NavMenuItems getSelectedNavMenuItem() {
		return NavMenuItems.TASKS;
	}
}
