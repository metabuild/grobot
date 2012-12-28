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

import java.util.List;

import org.metabuild.grobot.common.domain.TargetHost;
import org.metabuild.grobot.server.repository.TargetHostRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jburbridge
 * @since 11/20/2012
 */
@Service("targetHostService")
@Repository
@Transactional
public class TargetHostServiceImpl implements TargetHostService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TargetHostServiceImpl.class);
	
	@Autowired
	private TargetHostRepository targetHostRepository;

	@Override
	@Transactional(readOnly=true)
	public List<TargetHost> findAll() {
		return targetHostRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<TargetHost> findAllWithProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public TargetHost find(String id) {
		return targetHostRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public TargetHost findByName(String name) {
		final List<TargetHost> results =  targetHostRepository.findByName(name);
		return results.size() > 0 ? results.get(0) : null;
	}

	@Override
	@Transactional(readOnly=false)
	public TargetHost save(TargetHost targetHost) {
		LOGGER.info("Saving TargetHost with id {}", targetHost.getId());
		return targetHostRepository.save(targetHost);
	}

	@Override
	@Transactional(readOnly=false)
	public void delete(TargetHost targetHost) {
		LOGGER.info("Deleting TargetHost with id {}", targetHost.getId());
		targetHostRepository.delete(targetHost);
	}
}
