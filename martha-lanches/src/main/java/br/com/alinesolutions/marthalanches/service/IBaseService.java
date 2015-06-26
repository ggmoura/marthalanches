package br.com.alinesolutions.marthalanches.service;

import java.util.List;

public interface IBaseService<T, ID> {

	void persist(T entity);

	void merge(T entity);
	
	void remove(T entity);
	
	T find(ID id);
	
	List<T> listAll(Integer startPosition, Integer maxResult);
	
}
