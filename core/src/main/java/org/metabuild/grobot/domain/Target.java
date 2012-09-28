package org.metabuild.grobot.domain;

import java.util.List;

/**
 * Targets can be either hosts or groups of other targets (hosts or groups)
 * 
 * @author jburbrid
 * @since 9/27/2012
 */
public interface Target {

	public List<Target> getTargets();
}
