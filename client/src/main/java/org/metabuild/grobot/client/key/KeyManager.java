package org.metabuild.grobot.client.key;

/**
 * @author jburbridge
 * @since 11/21/2021
 */
public interface KeyManager {

	/**
	 * Attempts to load the key value. If no key is found, returns null.
	 */
	public String loadKey();
	
	/**
	 * Write a key value to the local overwriting any previously existing values.
	 */
	public void storeKey(String key);
	
}
