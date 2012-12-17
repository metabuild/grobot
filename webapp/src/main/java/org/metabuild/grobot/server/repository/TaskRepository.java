package org.metabuild.grobot.server.repository;

import java.util.List;

import org.metabuild.grobot.common.domain.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jburbridge
 * @since 12/16/2012
 */
public interface TaskRepository extends CrudRepository<Task, String> {

	public List<Task> findAll();
	
	public Task findById(String id);

	public List<Task> findByName(String name);
	
}
