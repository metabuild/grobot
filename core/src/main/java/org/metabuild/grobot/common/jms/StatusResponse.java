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
package org.metabuild.grobot.common.jms;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import org.metabuild.grobot.common.domain.BotStatus;

/**
 * A response to a status update request from the Grobot server, containing system details about the target host
 * 
 * @author jburbridge
 * @since 10/11/2012
 */
public class StatusResponse implements Serializable {
	
	private static final long serialVersionUID = 1217212553440921781L;
	
	private final String hostname;
	private final Properties systemProperties;
	private final Properties otherProperties;
	private final long timeStamp;
	private final BotStatus status;

	/**
	 * Default constructor, sets the hostname to "localhost" and creates empty maps
	 */
	public StatusResponse() {
		this("localhost", new Properties(), new Properties());
	}
	
	/**
	 * @param hostname the hostname of the system sending the status response
	 * @param systemProperties a map populated with system properties
	 * @param otherProperties a map populated with additional properties
	 */
	public StatusResponse(String hostname, Properties systemProperties, Properties otherProperties) {
		this.hostname = hostname;
		this.systemProperties = systemProperties;
		this.otherProperties = otherProperties;
		this.timeStamp = new Date().getTime();
		this.status = BotStatus.IDLE;
	}
	/**
	 * @return the hostname of the system sending the status response
	 */
	public String getHostname() {
		return hostname;
	}
	/**
	 * @return the systemProperties
	 */
	public Properties getSystemProperties() {
		return systemProperties;
	}
	/**
	 * @return the otherProperties
	 */
	public Properties getOtherProperties() {
		return otherProperties;
	}

	/**
	 * @return the timeStamp
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @return the status
	 */
	public BotStatus getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StatusResponse [hostname=" + hostname + "]";
	}
}
