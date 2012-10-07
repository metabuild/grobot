/**
 * 
 */
package org.metabuild.grobot.tasks;

import java.util.List;

/**
 * @author jburbridge
 * @since 10/7/2012
 */
public interface TaskFactory {

	/**
	 * @return a list of tasks
	 */
	public List<Task> getTasks();
	
}
