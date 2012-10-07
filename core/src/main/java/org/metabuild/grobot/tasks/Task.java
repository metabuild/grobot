/**
 * 
 */
package org.metabuild.grobot.tasks;

/**
 * @author jburbridge
 * @since 10/7/2012
 */
public interface Task {

	/**
	 * Runs the task and provides a plain object as a return value
	 * @return
	 */
	public Object run();
	
	/**
	 * @return true if the task has been run
	 */
	public boolean hasRun();

	/**
	 * @return the number of times the task has been run
	 */
	public int getTimesRun();
	
	/**
	 * @return the string representation of this task, usually the file name
	 */
	public String toString();
}
