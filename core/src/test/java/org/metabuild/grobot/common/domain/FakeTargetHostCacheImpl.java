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
package org.metabuild.grobot.common.domain;

import java.util.HashMap;
import java.util.Map;

import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.common.domain.TargetHostCacheImpl;

/**
 * Bootstraps a map of fake/dead targets for testing purposes
 * 
 * @author jburbridge
 * @since 10/20/2012
 */
@Deprecated
public class FakeTargetHostCacheImpl extends TargetHostCacheImpl {

	private static final Map<String, TargetHost> targets = new HashMap<String, TargetHost>();

	public FakeTargetHostCacheImpl(int numberOfTargets) {
		super(generateTargets(numberOfTargets));
	}
	
	private static Map<String, TargetHost> generateTargets(int numberOfTargets) {
		int x = 0;
		while (x++ < numberOfTargets) {
			TargetHost targetHost = new TargetHost("fakehostname" + x, "fake.host.address" + x, false);
			targets.put(targetHost.getName(), targetHost);
		}
		return targets;
	}
}
