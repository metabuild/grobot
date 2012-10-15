package org.metabuild.grobot.webapp.controllers;

import javax.jms.JMSException;

import org.metabuild.grobot.server.mq.StatusRequestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jburbridge
 * @since 10/12/2012
 */
@Controller
public class StatusController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);
	
	@Autowired
	StatusRequestProducer producer;

	/**
	 * Display the form
	 */
	@RequestMapping(value="/status/index", method=RequestMethod.GET)
	public void index() {
		try {
			producer.sendStatusRequest();
		} catch (JMSException e) {
			LOGGER.error("JMS Exception caught while attempting to send status request", e);
		}
	}

	
	/**
	 * Display the form
	 */
	@RequestMapping(value="/status/index", method=RequestMethod.POST)
	public void list() {}

}
