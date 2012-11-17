package org.metabuild.grobot.server.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.metabuild.grobot.domain.TargetHost;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("targetHostService")
@Repository
@Transactional
public class TargetHostServiceImpl implements TargetHostService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly=true)
	public List<TargetHost> findAll() {
		return entityManager.createQuery("from " + TargetHost.class.getName())
				.getResultList();
	}

	@Override
	public List<TargetHost> findAllWithProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TargetHost find(String id) {
		return null;
	}

	@Override
	public TargetHost save(TargetHost targetHost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TargetHost targetHost) {
		// TODO Auto-generated method stub

	}

}
