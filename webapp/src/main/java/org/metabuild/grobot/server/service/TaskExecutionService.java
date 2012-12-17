package org.metabuild.grobot.server.service;

import java.util.List;

import org.metabuild.grobot.common.domain.Task;
import org.metabuild.grobot.common.domain.TaskExecution;

/**
 * @author jburbridge
 * @since 12/16/2012
 */
public interface TaskExecutionService {

	public List<TaskExecution> findAll();
	
	public List<TaskExecution> findByTask(Task task);
	
	public TaskExecution find(String id);
	
	public TaskExecution save(TaskExecution taskExecution);
	
	public void delete(TaskExecution taskExecution);
}
