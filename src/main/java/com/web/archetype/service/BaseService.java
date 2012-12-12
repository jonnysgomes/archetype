package com.web.archetype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.web.archetype.idao.IBaseDao;
import com.web.archetype.iservice.IBaseService;

@Service("baseService")
public class BaseService<Entity> implements IBaseService<Entity> {

	@Autowired
	@Qualifier("baseDao")
	private IBaseDao<Entity> baseDao;
	
	@Override
	public void save(Entity entity) {
		baseDao.save(entity);
	}

	@Override
	public void update(Entity entity) {
		baseDao.update(entity);
	}

	@Override
	public void delete(Entity entity) {
		baseDao.delete(entity);
	}

}
