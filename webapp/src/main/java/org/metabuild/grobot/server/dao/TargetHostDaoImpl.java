package org.metabuild.grobot.server.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.metabuild.grobot.domain.TargetHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jburbridge
 * @since 11/12/2012
 */
@Repository(value="targetHostDao")
@Transactional
public class TargetHostDaoImpl implements TargetHostDao {

	private static final Class<TargetHost> clazz = TargetHost.class;
	private SessionFactory sessionFactory;
	
	@Override
	public List<TargetHost> findAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + clazz.getName())
				.list();	
	}

	@Override
	public TargetHost find(Long id) {
		return (TargetHost) sessionFactory.getCurrentSession()
				.get(clazz, id);
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<TargetHost> findByName(String name) {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + clazz.getName() + " where name like :name")
				.list();	
	}

	@Override
	public String findNameById(Long id) {
		return find(id).getName();
	}
}
