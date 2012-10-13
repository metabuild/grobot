package org.metabuild.grobot.client;

import org.metabuild.grobot.client.config.ClientJmsConfig;
import org.metabuild.grobot.client.mq.PingRequestListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jburbridge
 *
 */
public class GrobotClientRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting Grobot Client");
		ApplicationContext context = new AnnotationConfigApplicationContext(ClientJmsConfig.class);
		PingRequestListener listener = (PingRequestListener) context.getBean("pingRequestListner");
		System.out.println("Running...");
	}

}
