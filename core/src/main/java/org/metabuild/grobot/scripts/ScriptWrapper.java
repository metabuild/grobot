package org.metabuild.grobot.scripts;

/**
 * @author jburbridge
 * @since 10/7/2012
 */
public interface ScriptWrapper {

	/**
	 * @return the task's name
	 */
	public String getName();
	
	/**
	 * Runs the task
	 * 
	 * @return the return value of the task run
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
	 * @return the md5 hash of the underlying script / file
	 */
	public String getHash();
	
	/**
	 * @return the string representation of this task, usually the file name
	 */
	@Override
	public String toString();
}
