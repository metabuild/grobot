package org.metabuild.grobot.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fake bot spawner for testing client/server locally
 * 
 * @author jburbridge
 * @since 10/12/2012
 */
public class BotSpawner {

	private static final Logger LOGGER = LoggerFactory.getLogger(BotSpawner.class);
	private static final int NUMBER_OF_BOTS = 20;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 0;
		while (i++ < NUMBER_OF_BOTS) {
			String botName = "fake-bot1-" + i;
			System.setProperty("hostname", botName);
			startBotThread(botName);
		}
	}
	
	private synchronized static void startBotThread(String name) {
		LOGGER.info("{} spawning new bot {}", BotSpawner.class.getName(), name);
		new Thread(new GrobotClient(name)).start();
	}
}
