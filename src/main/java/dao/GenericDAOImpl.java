package dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GenericDAOImpl<T> implements GenericDAO<T> {

	private Class<T> persistentClass;

	@Autowired
	private SessionFactory sessionFactory; 
	
	public GenericDAOImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	public Class<T> getPersistentClass() {
		return this.persistentClass;
	}
	
	public void saveOrUpdate(T entity) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	public T getById(int id) {
		return this.sessionFactory.getCurrentSession().get(this.getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return (List<T>) this.sessionFactory.getCurrentSession().createQuery("from " + this.getPersistentClass().getName()).list();
	}

	public void delete(T entity) {
		this.sessionFactory.getCurrentSession().delete(entity);
	}
}
