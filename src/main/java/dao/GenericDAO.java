package dao;

import java.util.List;

public interface GenericDAO<T> {

	public void saveOrUpdate(T entity);
	public T getById(int id);
	public List<T> getAll();
	public void delete(T entity);

}
