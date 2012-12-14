package org.metabuild.grobot.common.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author jburbridge
 * @since 12/14/2012
 */
@Entity
@Table(name="TASK_EXECUTIONS")
public class TaskExecution implements Serializable {

	private static final long serialVersionUID = 4411655174194870856L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID")
	private String id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULED_START_TIME")
	private Date scheduleStartTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "ACTUAL_START_TIME")
	private Date actualStartTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME")
	private Date endTime;

	/**
	 * @param scheduleStartTime
	 * @param actualStartTime
	 * @param endTime
	 */
	public TaskExecution(Date scheduleStartTime, Date actualStartTime, Date endTime) {
		this.id = UUID.randomUUID().toString();
		this.scheduleStartTime = scheduleStartTime;
		this.actualStartTime = actualStartTime;
		this.endTime = endTime;
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
	 * @return the scheduleStartTime
	 */
	public Date getScheduleStartTime() {
		return scheduleStartTime;
	}

	/**
	 * @param scheduleStartTime the scheduleStartTime to set
	 */
	public void setScheduleStartTime(Date scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}

	/**
	 * @return the actualStartTime
	 */
	public Date getActualStartTime() {
		return actualStartTime;
	}

	/**
	 * @param actualStartTime the actualStartTime to set
	 */
	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
