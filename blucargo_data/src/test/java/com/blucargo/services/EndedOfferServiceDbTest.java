package com.blucargo.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.EndedOffer;
import com.blucargo.services.AcceptedOfferService;
import com.blucargo.services.CargoOfferService;
import com.blucargo.services.EndedOfferService;
import com.blucargo.services.OfferAcceptanceService;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class EndedOfferServiceDbTest extends TransactionRollbackTestBase{

	@Inject
    OfferAcceptanceService offerAcceptanceService;
	@Inject
	CargoOfferService cargoOfferService;
	@Inject
	AcceptedOfferService acceptedOfferService;
	@Inject
	EndedOfferService endedOfferService;
	
	@Before
	public void setUp(){
	}
	
	@Test
	public void testRemoveEndedOffer(){
		CargoOffer co = new CargoOffer();
		co.setOwner("b");
		cargoOfferService.save(co);
		
		EndedOffer endedOffer = new EndedOffer();
		endedOffer.setUserName("a");
		endedOffer.setOfferId(co.getId());
		endedOfferService.save(endedOffer);

		List<EndedOffer> endedOffers = endedOfferService.findAll();
		assertEquals(endedOffers.get(0).getUserName(),"a");
		assertEquals(endedOfferService.getEndedCargoOffersByOwner("a").size(),1);
		
		endedOfferService.removeEndedOffer(endedOffer);
		List<EndedOffer> endedOffers2 = endedOfferService.findAll();
		assertEquals(endedOffers2.get(0).getVisible(),new Integer(0));
		assertEquals(endedOfferService.getEndedCargoOffersByOwner("a").size(),0);
	}

	@Test
	public void testRemoveEndedOfferByCargoOfferAndOwner(){
		CargoOffer co = new CargoOffer();
		co.setOwner("b");
		cargoOfferService.save(co);
		
		EndedOffer endedOffer = new EndedOffer();
		endedOffer.setUserName("b");
		endedOffer.setOfferId(co.getId());
		endedOfferService.save(endedOffer);
		
		List<EndedOffer> endedOffers = endedOfferService.findAll();
		
		endedOfferService.removeEndedOfferByCargoOfferAndOwner(co, "a");
		getEntityManager().flush();
		
		List<EndedOffer> endedOffers2 = endedOfferService.findAll();
		
		assertEquals(endedOffers2.get(0).getUserName(),"b");
		assertEquals(endedOfferService.getEndedCargoOffersByOwner("a").size(),0);
	}
	
	@Test
	public void testRemoveEndedOfferByCargoOfferAndOwner2(){
		CargoOffer co = new CargoOffer();
		co.setOwner("b");
		cargoOfferService.save(co);
		
		EndedOffer endedOffer = new EndedOffer();
		endedOffer.setUserName("c");
		endedOffer.setOfferId(co.getId());
		endedOfferService.save(endedOffer);
		
		endedOfferService.removeEndedOfferByCargoOfferAndOwner(co, "c");

		assertEquals(endedOfferService.getEndedCargoOffersByOwner("c").size(),0);
		
	}
	
	@Test
	public void testForInsertingNonUniqueValue(){
		EndedOffer endedOffer = new EndedOffer();
		endedOffer.setOfferId(301L);
		endedOffer.setUserName("a");
		
		endedOfferService.save(endedOffer);
		getEntityManager().flush();
		
		EndedOffer endedOffer2 = new EndedOffer();
		endedOffer2.setOfferId(301L);
		endedOffer2.setUserName("a");
		
		endedOfferService.save(endedOffer2);
		getEntityManager().flush();
	}
	
}
