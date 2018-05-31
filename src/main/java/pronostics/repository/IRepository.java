package pronostics.repository;

import java.util.List;

public interface IRepository<T> {
	
	T findById(long id);
	
	void save(T t);
	
	void delete(long id);
	
	void update(T t);
	
	List<T> findAll();
}
