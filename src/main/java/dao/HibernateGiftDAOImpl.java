package dao;

import business.Gift;

public class HibernateGiftDAOImpl extends GenericDAOImpl<Gift> implements GiftDAO {
	
	public HibernateGiftDAOImpl() {
		super(Gift.class);
	}

}
