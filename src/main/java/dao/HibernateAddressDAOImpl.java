package dao;

import java.util.List;

import business.Address;

public class HibernateAddressDAOImpl extends GenericDAOImpl<Address> implements AddressDAO {
	
	public HibernateAddressDAOImpl() {
		super(Address.class);
	}

}
