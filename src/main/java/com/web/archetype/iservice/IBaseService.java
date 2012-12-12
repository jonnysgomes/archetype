package com.web.archetype.iservice;

public interface IBaseService<Entity> {

	public void save(Entity entity);
	
	public void update(Entity entity);
	
	public void delete(Entity entity);
	
}
