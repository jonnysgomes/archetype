package com.web.archetype.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.archetype.idao.IBaseDao;

@Repository("baseDao")
public class BaseDao<Entity> implements IBaseDao<Entity> {

	@PersistenceContext
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Transactional
	public void save(Entity entity) {
		entityManager.persist(entity);
	}

	@Transactional
	public void update(Entity entity) {
		entityManager.merge(entity);
	}

	@Transactional
	public void delete(Entity entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

}
