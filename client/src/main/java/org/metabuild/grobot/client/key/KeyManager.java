/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
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
