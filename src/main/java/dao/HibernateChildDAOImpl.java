package dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import business.Child;
import business.Gift;

public class HibernateChildDAOImpl extends GenericDAOImpl<Child> implements ChildDAO {
	
	public HibernateChildDAOImpl() {
		super(Child.class);
	}

}
