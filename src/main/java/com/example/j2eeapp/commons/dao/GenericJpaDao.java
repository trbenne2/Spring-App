package com.example.j2eeapp.commons.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.example.j2eeapp.commons.domain.BaseEntity;

/**
 * Provides generic common implementation of GenericDao interface persistence methods
 * Extend this abstract class to implement Dao for you specific needs
 * 
 * @author Tyler Bennett
 *
 */

@Transactional
public abstract class GenericJpaDao<T, ID extends Serializable> implements GenericDao<T, ID> {
	private Class<T> persistentClass;

	// hibernate manager while be injected
	private EntityManager entityManager;
	
	public GenericJpaDao(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	// Don't want to give access from outside except classes that extend 
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	// encapsulates the manager entity find method 
	@Transactional(readOnly=true)
	public T findById(ID id) {
		T entity= (T) getEntityManager().find(getPersistentClass(), id);
		return entity;
	}
	
	// encapsulated get results list and return list of entities, hyperSQL hibernateSQL
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<T> findAll(){
		return getEntityManager()
				.createQuery("select x from " + getPersistentClass().getSimpleName() + " x")
				.getResultList();
	}
	
	// Encapsulated persist method 
	public T save(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}
	
	public T update(T entity) {
		T mergedEntity = getEntityManager().merge(entity);
		return(mergedEntity);
	}
	
	public void delete(T entity) {
		if(BaseEntity.class.isAssignableFrom(persistentClass)) {
			getEntityManager().remove(
					getEntityManager().getReference(entity.getClass(),
							((BaseEntity)entity).getId()));
		}
	}
	
	public void flush() {
		getEntityManager().flush();
	}
	
}
