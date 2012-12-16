package org.metabuild.grobot.common.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author jburbridge
 * @since 12/14/2012
 */
@Entity
@Table(name="TASKS")
public class Task  implements Serializable {

	private static final long serialVersionUID = -6750099046329133365L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SCRIPT_NAME")
	private String scriptName;
	
	@OneToMany(mappedBy="id", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<TaskExecution> taskExecutions;

	/**
	 * No-arg constructor for Hibernate
	 */
	public Task() {}
	
	/**
	 * @param id
	 * @param name
	 * @param scriptName
	 * @param taskExecutions
	 */
	public Task(String name, String scriptName, List<TaskExecution> taskExecutions) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.scriptName = scriptName;
		this.taskExecutions = taskExecutions;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the scriptName
	 */
	public String getScriptName() {
		return scriptName;
	}

	/**
	 * @param scriptName the scriptName to set
	 */
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	/**
	 * @return the taskExecutions
	 */
	public List<TaskExecution> getTaskExecutions() {
		return taskExecutions;
	}

	/**
	 * @param taskExecutions the taskExecutions to set
	 */
	public void setTaskExecutions(List<TaskExecution> taskExecutions) {
		this.taskExecutions = taskExecutions;
	}
}
