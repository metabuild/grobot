package org.metabuild.grobot.webapp.controllers;

import java.util.List;

import javax.jms.JMSException;

import org.metabuild.grobot.domain.TargetHost;
import org.metabuild.grobot.server.dao.TargetHostDao;
import org.metabuild.grobot.server.mq.StatusRequestProducer;

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
	private TargetHostDao targetHostDao;

	@Autowired
	private StatusRequestProducer producer;

	/**
	 * Display the list of targets
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel) {
		
		final List<TargetHost> targets = targetHostDao.findAll();
		uiModel.addAttribute("targets", targets);
		addSelectedMenuItem(uiModel);
		
		try {
			producer.sendStatusRequest();
		} catch (JMSException e) {
			LOGGER.error("JMS Exception caught while attempting to send a status update request", e);
		}
		return BOTS_LIST_VIEW;
	}
	
	/**
	 * Display the details of a target
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String details(@PathVariable("id") Long id, Model uiModel) {
		
		final TargetHost target = targetHostDao.find(id);
		uiModel.addAttribute("target", target);
		addSelectedMenuItem(uiModel);
		
		return BOTS_DETAILS_VIEW;
	}

	@Override
	public NavMenuItems getSelectedNavMenuItem() {
		return NavMenuItems.BOTS;
	}
}
