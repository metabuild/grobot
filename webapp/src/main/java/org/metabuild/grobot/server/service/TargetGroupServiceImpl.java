package org.metabuild.grobot.server.service;

import java.util.List;

import org.metabuild.grobot.common.domain.TargetGroup;
import org.metabuild.grobot.server.repository.TargetGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author jburbridge
 * @since 12/16/2012
 */
@Service("targetGroupService")
@Repository
@Transactional
public class TargetGroupServiceImpl implements TargetGroupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TargetGroupServiceImpl.class);
	
	@Autowired
	private TargetGroupRepository targetGroupRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<TargetGroup> findAll() {
		return targetGroupRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public TargetGroup find(String id) {
		return targetGroupRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public TargetGroup findById(String id) {
		return find(id);
	}

	@Override
	@Transactional(readOnly=true)
	public TargetGroup findByName(String name) {
		final List<TargetGroup> results = targetGroupRepository.findByName(name);
		return results.size() > 0 ? results.get(0) : null;
	}

	@Override
	@Transactional(readOnly=false)
	public TargetGroup save(TargetGroup targetGroup) {
		LOGGER.info("Saving TargetGroup with id {}", targetGroup.getId()); 
		return targetGroupRepository.save(targetGroup);
	}

	@Override
	@Transactional(readOnly=false)
	public void delete(TargetGroup targetGroup) {
		LOGGER.info("Deleting TargetGroup with id {}", targetGroup.getId());
		targetGroupRepository.delete(targetGroup);
	}
}
