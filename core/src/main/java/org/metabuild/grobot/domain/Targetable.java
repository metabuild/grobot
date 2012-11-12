package org.metabuild.grobot.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Targets can be either hosts or groups of other targets (hosts or groups)
 * 
 * @author jburbrid
 * @since 9/27/2012
 */
public interface Targetable extends Serializable {

	public String getName();
	public boolean isActive();
	public List<Targetable> getTargets();
}
