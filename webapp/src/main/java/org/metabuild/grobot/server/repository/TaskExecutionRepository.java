package org.metabuild.grobot.server.repository;

import java.util.List;

import org.metabuild.grobot.common.domain.Task;
import org.metabuild.grobot.common.domain.TaskExecution;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jburbridge
 * @since 12/16/2012
 */
public interface TaskExecutionRepository extends CrudRepository<TaskExecution, String> {

	public List<TaskExecution> findAll();
	
	public TaskExecution findById(String id);

	public List<TaskExecution> findByTask(Task task);
	
}
