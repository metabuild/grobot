package org.metabuild.grobot.server.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.metabuild.grobot.common.domain.TargetHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jburbridge
 * @since 11/20/2012
 */
@Service("targetHostService")
@Repository
@Transactional
public class TargetHostServiceImpl implements TargetHostService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TargetHostServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly=true)
	public List<TargetHost> findAll() {
		final List<TargetHost> targetHosts = entityManager.createNamedQuery("TargetHost.findAll", TargetHost.class)
				.getResultList();
		return targetHosts;
	}

	@Override
	public List<TargetHost> findAllWithProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TargetHost find(String id) {
		TargetHost targetHost = null;
		try {
			targetHost = entityManager.createNamedQuery("TargetHost.findById", TargetHost.class)
				.setParameter("id", id)
				.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info("No TargetHost found for id {}", id);
		}
		return targetHost;
	}
	
	@Override
	public TargetHost findByName(String hostname) {
		TargetHost targetHost = null;
		try {
			targetHost = entityManager.createNamedQuery("TargetHost.findByName", TargetHost.class)
				.setParameter("hostname", hostname)
				.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info("No TargetHost found with hostname {}", hostname);
		}
		return targetHost;
	}

	@Override
	public TargetHost save(TargetHost targetHost) {
		if (targetHost.getId() == null) {
			LOGGER.info("Inserting new TargetHost {}", targetHost.getName());
			entityManager.persist(targetHost);
		} else {
			LOGGER.info("Updating existing TargetHost {}", targetHost.getName());
			entityManager.merge(targetHost);
		}
		LOGGER.info("TargetHost saved with id {}", targetHost.getId());
		return targetHost;
	}

	@Override
	public void delete(TargetHost targetHost) {
		TargetHost mergedTargetHost = entityManager.merge(targetHost);
		entityManager.remove(mergedTargetHost);
		LOGGER.info("Deleted TargetHost with id {}", mergedTargetHost.getId());
	}
}
