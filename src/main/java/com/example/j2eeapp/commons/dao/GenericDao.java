package com.example.j2eeapp.commons.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic interfaces for Data Access Objects. To be Extended or implemented
 * Contains common persistence methods.
 * 
 * @author Tyler Bennett
 *
 */
public interface GenericDao<T, ID extends Serializable> {
	T save(T entity);
	T update(T entity);
	void delete(T entity);
	T findById(ID id);
	List<T> findAll();
	void flush();
}
