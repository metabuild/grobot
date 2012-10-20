package org.metabuild.grobot.webapp.controllers;

import java.util.List;

import javax.jms.JMSException;

import org.metabuild.grobot.domain.Target;
import org.metabuild.grobot.domain.TargetCacheImpl;
import org.metabuild.grobot.domain.TargetHost;
import org.metabuild.grobot.server.mq.StatusRequestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
@RequestMapping("/status")
@Controller
public class StatusController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);

	@Autowired
	private TargetCacheImpl targetCacheImpl;

	@Autowired
	private StatusRequestProducer producer;

	/**
	 * Display the list of targets
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel) {
		
		List<TargetHost> targets = 	targetCacheImpl.getAll();
		uiModel.addAttribute("targets", targets);
		
		try {
			producer.sendStatusRequest();
		} catch (JMSException e) {
			LOGGER.error("JMS Exception caught while attempting to send status request", e);
		}
		return "status/list";
	}
}
