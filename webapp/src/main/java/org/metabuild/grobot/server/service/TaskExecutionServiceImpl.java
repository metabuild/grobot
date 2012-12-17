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
