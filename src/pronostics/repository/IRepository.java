package pronostics.repository;

import java.util.List;

public interface IRepository<T> {
	void save(T t);
	
	T load(long id);
	
	void delete(long id);
	
	void update(T t);
	
	List<T> loadAll();
}
