/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.metabuild.grobot.common.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author jburbrid
 * @since 9/27/2012
 */
@Entity
@Table(name="BOT_GROUPS")
public class BotGroup implements Serializable {

	private static final long serialVersionUID = -8111761490319291460L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ACTIVE")
	private boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "BOT_GROUP_MEMBERS",
		joinColumns = @JoinColumn(name = "BOT_GROUP_ID"),
		inverseJoinColumns = @JoinColumn(name = "BOT_ID"))
	private Set<Bot> bots = new HashSet<Bot>();
	
	@Transient
	private BotGroup parent;

	/**
	 * No-arg constructor for Hibernate
	 */
	public BotGroup() {}
	
	/**
	 * @param name - the group's name
	 * @param bots - a list of bots
	 * @param parent - a parent group
	 * @param active - is the group available for targeting
	 */
	public BotGroup(String name, Set<Bot> bots, BotGroup parent, boolean active) {
		this.name = name;
		this.bots = bots;
		this.parent = parent;
		this.active = active;
	}

	/**
	 * @param name - the group's name
	 */
	public BotGroup(String name) {
		this(name, Collections.synchronizedSet(new HashSet<Bot>()));
	}

	/**
	 * @param name - the group's name
	 * @param bots - a list of bots
	 */
	public BotGroup(String name, Set<Bot> bots) {
		this(name, bots, null);
	}

	/**
	 * @param name - the group's name
	 * @param bots - a list of bots
	 * @param parent - a parent group
	 */
	public BotGroup(String name, Set<Bot> bots, BotGroup parent) {
		this(name, bots, parent, true);
	}

	/**
	 * @param bot
	 */
	public void addBot(Bot bot) {
		this.bots.add(bot);
	}
	
	/**
	 * @param bots
	 */
	public void addBots(Set<Bot> bots) {
		this.bots.addAll(bots);
	}
	
	/**
	 * @return bots
	 */
	public Set<Bot> getBots() {
		return bots;
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
	 * @return the parent
	 */
	public BotGroup getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(BotGroup parent) {
		this.parent = parent;
	}

	/**
	 * @param bots the targets to set
	 */
	public void setTargetHosts(Set<Bot> bots) {
		this.bots = bots;
	}

	/**
	 * @return active
	 */
	public boolean isActive() {
		return active;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BotGroup [id=").append(id).append(", name=")
				.append(name).append(", active=").append(active).append("]");
		return builder.toString();
	}
}
