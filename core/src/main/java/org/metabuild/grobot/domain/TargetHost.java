package org.metabuild.grobot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import org.metabuild.grobot.mq.StatusResponse;

/**
 * @author jburbridge
 * @since 9/27/2012
 */
@Entity
@Table(name="TARGET_HOST")
public class TargetHost implements Targetable {
	
	private static final long serialVersionUID = 150135564407144746L;
	
	private Long id;
	private String name;
	private String address;
	private boolean active;
	private Properties systemProperties;
	private Properties otherProperties;
	private List<Targetable> targets;
	private Date registered;
	private DateTime lastUpdatedStatus;
	private TargetHostStatus status;

	/**
	 * Default constructor - initializes the object with empty properties
	 * 
	 * @param name
	 * @param address - the fully qualified host name
	 * @param active - is the host available for targeting
	 */
	public TargetHost(String name, String address, boolean active) {
		this.name = name;
		this.address = address;
		this.active = active;
		this.systemProperties = new Properties();
		this.otherProperties = new Properties();
		this.targets = new ArrayList<Targetable>();
		this.status = TargetHostStatus.STOPPED;
		targets.add(this);
	}
	
	/**
	 * Constructor for creating TargetHost from PingResponses
	 * 
	 * @param statusResponse
	 */
	public TargetHost(StatusResponse statusResponse) {
		this.name = statusResponse.getHostname();
		this.address = statusResponse.getHostname();
		this.systemProperties = statusResponse.getSystemProperties();
		this.otherProperties = statusResponse.getOtherProperties();
		this.lastUpdatedStatus = new DateTime(statusResponse.getTimeStamp());
		this.status = TargetHostStatus.IDLE;
		this.active = true;
	}

	/* (non-Javadoc)
	 * @see org.metabuild.grobot.domain.Target#getTargets()
	 */
	@Override
	public List<Targetable> getTargets() {
		return targets;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}
	
	/**
	 * @return the name
	 */
	@Column(name = "NAME")
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
	 * @return the address
	 */
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the systemProperties
	 */
	public Properties getSystemProperties() {
		return systemProperties;
	}

	/**
	 * @param systemProperties the systemProperties to set
	 */
	public void setSystemProperties(Properties systemProperties) {
		this.systemProperties = systemProperties;
	}

	/**
	 * @return the otherProperties
	 */
	public Properties getOtherProperties() {
		return otherProperties;
	}

	/**
	 * @param otherProperties the otherProperties to set
	 */
	public void setOtherProperties(Properties otherProperties) {
		this.otherProperties = otherProperties;
	}

	/**
	 * @return the registered date
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "REGISTERED")
	public Date getRegistered() {
		return registered;
	}

	/**
	 * @param registered the date to set
	 */
	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	/**
	 * @return the lastUpdatedStatus
	 */
	public DateTime getLastUpdatedStatus() {
		return lastUpdatedStatus;
	}

	/**
	 * @param lastUpdatedStatus the lastKnownResponse to set
	 */
	public void setLastUpdatedStatus(DateTime lastUpdatedStatus) {
		this.lastUpdatedStatus = lastUpdatedStatus;
	}

	/**
	 * @return the status
	 */
	public TargetHostStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(TargetHostStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
