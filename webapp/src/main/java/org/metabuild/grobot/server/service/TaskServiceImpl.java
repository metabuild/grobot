package org.metabuild.grobot.server.service;

import java.util.List;

import org.metabuild.grobot.common.domain.Task;
import org.metabuild.grobot.server.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jburbridge
 * @since 12/16/2012
 */
@Service("taskService")
@Repository
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	/* (non-Javadoc)
	 * @see org.metabuild.grobot.server.service.TaskService#findAll()
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see org.metabuild.grobot.server.service.TaskService#findByName(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Task> findByName(String name) {
		return taskRepository.findByName(name);
	}

	/* (non-Javadoc)
	 * @see org.metabuild.grobot.server.service.TaskService#find(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public Task find(String id) {
		return taskRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see org.metabuild.grobot.server.service.TaskService#save(org.metabuild.grobot.common.domain.Task)
	 */
	@Override
	@Transactional(readOnly=false)
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	/* (non-Javadoc)
	 * @see org.metabuild.grobot.server.service.TaskService#delete(org.metabuild.grobot.common.domain.Task)
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Task task) {
		taskRepository.delete(task);
	}
}
