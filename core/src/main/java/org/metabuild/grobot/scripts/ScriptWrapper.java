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
package org.metabuild.grobot.scripts;

/**
 * @author jburbridge
 * @since 10/7/2012
 */
public interface ScriptWrapper {

	/**
	 * @return the task's name
	 */
	public String getName();
	
	/**
	 * Runs the task
	 * 
	 * @return the return value of the task run
	 */
	public Object run();
	
	/**
	 * @return true if the task has been run
	 */
	public boolean hasRun();

	/**
	 * @return the number of times the task has been run
	 */
	public int getTimesRun();
	
	/**
	 * @return the md5 hash of the underlying script / file
	 */
	public String getHash();
	
	/**
	 * @return the string representation of this task, usually the file name
	 */
	@Override
	public String toString();
}
