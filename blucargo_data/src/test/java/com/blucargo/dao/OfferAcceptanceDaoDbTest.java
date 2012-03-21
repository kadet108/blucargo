package com.blucargo.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.dao.OfferAcceptanceDao;
import com.blucargo.model.OfferAcceptance;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class OfferAcceptanceDaoDbTest extends TransactionRollbackTestBase{

	@Inject
    OfferAcceptanceDao offerAcceptanceListDao;
	
	@Before
	public void setUp(){
	}

	@Test
	public void testSaveOfferAcceptance(){
		OfferAcceptance offerAcceptance = new OfferAcceptance();
		offerAcceptance.setInitiated("b");
		offerAcceptance.setInitiator("a");
		offerAcceptanceListDao.saveOrUpdate(offerAcceptance);
		
		assertNotNull(offerAcceptance.getId());
		OfferAcceptance offerAcceptance2 = offerAcceptanceListDao.findById(offerAcceptance.getId());
		assertEquals("b",offerAcceptance.getInitiated());
		assertEquals("a",offerAcceptance.getInitiator());
		
	}
	

	@Test
	public void testFind(){
		OfferAcceptance offerAcceptance = new OfferAcceptance();
		offerAcceptance.setInitiated("b");
		offerAcceptance.setInitiator("a");
		offerAcceptance.setOfferId(3L);
		offerAcceptanceListDao.saveOrUpdate(offerAcceptance);

		OfferAcceptance offerAcceptance2 = offerAcceptanceListDao.find("a","b",3L);
		assertNotNull(offerAcceptance2);
		assertEquals(offerAcceptance.getInitiator(),"a");
		assertEquals(offerAcceptance.getInitiated(),"b");
		assertEquals(offerAcceptance.getOfferId(),new Long(3));
	
	}
	
	@Test
	public void testFindRetrievesNull(){
		OfferAcceptance offerAcceptance = new OfferAcceptance();
		offerAcceptance.setInitiated("b");
		offerAcceptance.setInitiator("a");
		offerAcceptance.setOfferId(3L);
		offerAcceptanceListDao.saveOrUpdate(offerAcceptance);

		OfferAcceptance offerAcceptance2 = offerAcceptanceListDao.find("a","b",4L);
		assertNull(offerAcceptance2);
	
	}

	
}
