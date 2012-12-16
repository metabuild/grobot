package org.metabuild.grobot.server.service;

import java.util.List;

import org.metabuild.grobot.common.domain.Task;

/**
 * @author jburbridge
 * @since 12/16/2012
 */
public interface TaskService {

	public List<Task> findAll();
	
	public List<Task> findByName(String name);
	
	public Task find(String id);
	
	public Task save(Task task);
	
	public void delete(Task task);
}
