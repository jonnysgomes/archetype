package com.web.archetype.idao;

public interface IBaseDao<Entity> {
	
	public void save(Entity entity);
	
	public void update(Entity entity);
	
	public void delete(Entity entity);

}
