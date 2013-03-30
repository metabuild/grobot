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
package org.metabuild.grobot.webapp.controllers;

/**
 * @author jburbridge
 * @since 11/4/2012
 */
public enum NavMenuItems {

	SCHEDULE("schedule"),
	BOTS("bots"),
	GROUPS("groups"),
	TASKS("tasks"),
	CONFIG("config"),
	QUEUE("queue"),
	HELP("help");

	private String path;
	
	NavMenuItems(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	@Override
	public String toString() {
		return path;
	}
}
