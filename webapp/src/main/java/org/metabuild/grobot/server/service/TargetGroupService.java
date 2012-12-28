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
package org.metabuild.grobot.server.service;

import java.lang.annotation.Target;
import java.util.List;

import org.metabuild.grobot.common.domain.TargetGroup;

/**
 * 
 * @author jburbridge
 * @since 12/16/012
 */
public interface TargetGroupService {

	public List<TargetGroup> findAll();
	
	public TargetGroup find(String id);
	
	public TargetGroup findById(String id);
	
	public TargetGroup findByName(String name);
	
	public TargetGroup save(TargetGroup targetGroup);
	
	public void delete(TargetGroup targetGroup);
	
}
