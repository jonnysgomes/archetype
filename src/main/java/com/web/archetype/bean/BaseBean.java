package com.web.archetype.bean;

import java.lang.reflect.ParameterizedType;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.web.archetype.iservice.IBaseService;
import com.web.archetype.util.Criteria;
import com.web.archetype.util.ReflectionUtil;

@ManagedBean
@RequestScoped
@Controller("baseBean")
public abstract class BaseBean<Entity> {
	
	protected Entity entity;
	
	protected abstract Criteria getCriteria();
	
	@Autowired
	@Qualifier("baseService")
	protected IBaseService<Entity> baseService;
	
	private Class<Entity> persistentClass = null;
	
	public BaseBean() {
		init();
	}	
	
	public void init() {
		try {
			setEntity(getPersistentClass().newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Class<Entity> getPersistentClass() {
		if (persistentClass == null) {
			this.persistentClass = (Class<Entity>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return persistentClass;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	protected void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public String add() {
		getNewInstance();
		return "add?faces-redirect=true";
	}
	
	public String search() {
		getNewInstance();
		return "search?faces-redirect=true";
	}
	
	public void save() {
		String fieldName = ReflectionUtil.getFieldNameID(persistentClass);
		Object id = ReflectionUtil.getValueField(persistentClass, entity, fieldName);
		
		if (id == null) {
			baseService.save(entity);
			setEntity(entity);
			addMessage("Registro salvo com sucesso", FacesMessage.SEVERITY_INFO);
		} else {
			baseService.update(entity);
			addMessage("Registro atualizado com sucesso", FacesMessage.SEVERITY_INFO);
		}
		getNewInstance();
	}
	
	public void delete() {
		baseService.delete(entity);
		setEntity(entity);
		addMessage("Registro excluído com sucesso", FacesMessage.SEVERITY_INFO);
		getNewInstance();
	}
	
	protected void getNewInstance() {
		try {
			setEntity(getPersistentClass().newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

}
