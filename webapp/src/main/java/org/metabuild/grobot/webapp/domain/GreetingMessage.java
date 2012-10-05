package org.metabuild.grobot.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingMessage {

	@Autowired
	@Qualifier("helloMessage")
	private String greetingMessage;

	public GreetingMessage() {
	}
	
	public String getGreetingMessage() {
		return greetingMessage;
	}

	public void setGreetingMessage(String greetingMessage) {
		this.greetingMessage = greetingMessage;
	}

	@Override
	public String toString() {
		return greetingMessage;
	}
}
