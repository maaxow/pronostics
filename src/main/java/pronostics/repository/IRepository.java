package pronostics.repository;

import java.util.List;

public interface IRepository<T> {

	public T findById(long id);

	public List<T> findAll();

	public int save(T t);

	public int delete(long id);

	public int update(T t);

}
