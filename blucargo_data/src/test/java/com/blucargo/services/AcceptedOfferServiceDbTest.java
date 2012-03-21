package com.blucargo.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.model.AcceptedOffer;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.OfferAcceptance;
import com.blucargo.services.AcceptedOfferService;
import com.blucargo.services.CargoOfferService;
import com.blucargo.services.OfferAcceptanceService;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class AcceptedOfferServiceDbTest extends TransactionRollbackTestBase{

	@Inject
    OfferAcceptanceService offerAcceptanceService;
	@Inject
	CargoOfferService cargoOfferService;
	@Inject
	AcceptedOfferService acceptedOfferService;
	
	@Before
	public void setUp(){
	}

	@Test
	public void testGetAcceptedOfferByCargoOfferAndUserName(){
		CargoOffer co=new CargoOffer();
		co.setOwner("a");
		cargoOfferService.save(co);
		
		AcceptedOffer ao=new AcceptedOffer();
		ao.setOfferId(co.getId());
		ao.setUserName("b");
		acceptedOfferService.save(ao);

		AcceptedOffer acceptedOfferBack = acceptedOfferService.getAcceptedOfferByCargoOfferIdAndUserName(co.getId(),"b");
		assertEquals(acceptedOfferBack.getUserName(),"b");
		assertEquals(acceptedOfferBack.getOfferId(),co.getId());
	}
}
