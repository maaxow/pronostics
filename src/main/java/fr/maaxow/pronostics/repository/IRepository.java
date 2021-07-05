package fr.maaxow.pronostics.repository;

import java.util.List;

public interface IRepository<T> {
	
	T findById(long id);
	
	int save(T t);
	
	int delete(long id);
	
	int update(T t);
	
	List<T> findAll();
}
