package com.blucargo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.dao.AcceptedOfferDao;
import com.blucargo.dao.CargoOfferDao;
import com.blucargo.model.AcceptedOffer;
import com.blucargo.model.CargoOffer;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class AcceptedOfferDaoDbTest extends TransactionRollbackTestBase{

	@Inject
    AcceptedOfferDao acceptedOfferDao;

	@Inject
    CargoOfferDao cargoOfferDao;
    
	private CargoOffer co;
	private AcceptedOffer ao;
	
	@Before
	public void setUp(){
		co=new CargoOffer();
		co.setOwner("a");
		cargoOfferDao.saveOrUpdate(co);

		ao=new AcceptedOffer();
		ao.setOfferId(co.getId());
		ao.setUserName("b");
		acceptedOfferDao.saveOrUpdate(ao);
	}
	
	@Test
	public void testGetAcceptedCargoOffersByOwner(){
		List<AcceptedOffer> acceptedOffers=acceptedOfferDao.findAll();
		assertTrue(acceptedOffers.size()>0);
		assertEquals(acceptedOffers.get(0).getUserName(),"b");
		assertEquals(acceptedOffers.get(0).getOfferId(), co.getId());
	}

	@Test
	public void testRemoveAcceptedCargoOffersByOwner(){
		acceptedOfferDao.removeAcceptedOfferByCargoOfferAndOwner(co, "b");
		List<AcceptedOffer> acceptedOffers=acceptedOfferDao.findAll();
		assertTrue(acceptedOffers.size()==0);
		
	
	}
	
}
