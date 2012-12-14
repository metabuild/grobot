/**
 * 
 */
package org.metabuild.grobot.scripts;

import java.util.List;

/**
 * @author jburbridge
 * @since 10/7/2012
 */
public interface ScriptWrapperFactory {

	/**
	 * @return a list of scripts
	 */
	public List<ScriptWrapper> getScripts();
	
}
