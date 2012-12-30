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

import org.metabuild.grobot.common.domain.Task;
import org.metabuild.grobot.common.domain.TaskExecution;
import org.metabuild.grobot.server.repository.TaskExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jburbridge
 * @since 12/16/2012
 */
@Service("taskExecutionService")
@Repository
@Transactional
public class TaskExecutionServiceImpl implements TaskExecutionService {

	@Autowired
	private TaskExecutionRepository taskExecutionRepository;

	@Override
	public List<TaskExecution> findAll() {
		return taskExecutionRepository.findAll();
	}

	@Override
	public List<TaskExecution> findByTask(Task task) {
		return taskExecutionRepository.findByTask(task);
	}

	@Override
	public TaskExecution find(String id) {
		return taskExecutionRepository.findById(id);
	}

	@Override
	public TaskExecution save(TaskExecution taskExecution) {
		return taskExecutionRepository.save(taskExecution);
	}

	@Override
	public void delete(TaskExecution taskExecution) {
		taskExecutionRepository.delete(taskExecution);
	}
}
