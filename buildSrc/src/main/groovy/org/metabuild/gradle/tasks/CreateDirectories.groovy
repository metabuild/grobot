package org.metabuild.gradle.tasks;

import java.io.File;
import java.util.List;

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectories;
import org.gradle.api.tasks.TaskAction;

import java.util.logging.Logger;


/**
 * Creates directories if they don't already exist
 *
 * @author jburbridge
 * @since 9/27/2012
 */
class CreateDirectories extends DefaultTask {

	Logger logger = Logger.getLogger("CreateDirectories")
	
	@OutputDirectories
	List<File> directories

	@TaskAction
	def createDirectories() {
		directories.each { File directory ->
			if (!directory.exists()) {
				logger.warn("Creating ${directory}")
				directory.mkdirs()
			}
		}
	}
}
