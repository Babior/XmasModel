package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import business.Address;
import business.Child;
import business.Gift;
import dao.AddressDAO;
import dao.ChildDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("DAOTest-context.xml")
@Transactional
public class ChildDAOTest {
	
	@Autowired
	private ChildDAO childDAO;
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Test 
	public void testChildDAO() {
		Address address = new Address();
		address.setAddress("Straße 120");
		addressDAO.saveOrUpdate(address);
		
		Child child1 = new Child();
		child1.setAddress(address);
		child1.setName("Anna");
		
		int len1 = childDAO.getAll().size();
		childDAO.saveOrUpdate(child1);
		int len2 = childDAO.getAll().size();
		
		assertEquals(len1 + 1, len2);
		
		Child child2 = this.childDAO.getById(child1.getId());
		assertEquals(child1.getName(), child2.getName());
		assertEquals(child1, child2);
		
		this.childDAO.delete(child1);
		assertEquals(len1, this.childDAO.getAll().size());
	}
	
	@Test
	public void shouldTestGiftChildRelation() {
		Address address = new Address();
		address.setAddress("Straße 120");
		addressDAO.saveOrUpdate(address);
		
		Child child1 = new Child();
		child1.setAddress(address);
		child1.setName("Anna");
		
		this.childDAO.saveOrUpdate(child1);
		
		Gift gift1 = new Gift();
		gift1.setDescription("Gift test");
		child1.addGift(gift1);
		
		Child child2 = this.childDAO.getById(child1.getId());
		assertEquals(child1.getGifts().size(), child2.getGifts().size());
		
		child2.removeGift(gift1);
		assertEquals(child1.getGifts().size(), child2.getGifts().size());
	}
}
