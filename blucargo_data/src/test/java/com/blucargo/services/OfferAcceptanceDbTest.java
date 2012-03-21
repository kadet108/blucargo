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
public class OfferAcceptanceDbTest extends TransactionRollbackTestBase{

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
	public void testCreateOfferAcceptance(){
		OfferAcceptance offer=new OfferAcceptance();
		offer.setInitiator("a");
		offer.setInitiated("b");
		offerAcceptanceService.save(offer);
		
		OfferAcceptance offer2=offerAcceptanceService.findAll().get(0);
		assertEquals(offer2.getInitiator(),"a");
		assertEquals(offer2.getInitiated(),"b");
		
	}
	
	@Test
	public void testInitializerLogsOut(){
		OfferAcceptance offer=new OfferAcceptance();
		offer.setInitiator("a");
		offer.setInitiated("b");
		offerAcceptanceService.save(offer);

		assertEquals(1,offerAcceptanceService.findAll().size());
		offerAcceptanceService.initiatorLogsOut("a");
		assertEquals(0,offerAcceptanceService.findAll().size());
	}

	@Test
	public void testInitializerLogsOut2(){
		OfferAcceptance offer1=new OfferAcceptance();
		offer1.setInitiator("a");
		offer1.setInitiated("b");
		offer1.setOfferId(1L);
		
		OfferAcceptance offer2=new OfferAcceptance();
		offer2.setInitiator("a");
		offer2.setInitiated("c");
		offer2.setOfferId(2L);

		OfferAcceptance offer3=new OfferAcceptance();
		offer3.setInitiator("b");
		offer3.setInitiated("a");
		offer3.setOfferId(3L);

		OfferAcceptance offer4=new OfferAcceptance();
		offer4.setInitiator("b");
		offer4.setInitiated("c");
		offer4.setOfferId(4L);
		
		offerAcceptanceService.save(offer1);
		offerAcceptanceService.save(offer2);
		offerAcceptanceService.save(offer3);
		offerAcceptanceService.save(offer4);
		
		assertEquals(4,offerAcceptanceService.findAll().size());
		offerAcceptanceService.initiatorLogsOut("a");
		assertEquals(2,offerAcceptanceService.findAll().size());
	}

	
	@Test
	public void testInitializeOffer(){
		CargoOffer co=new CargoOffer();
		co.setAddressFrom("Opole");
		cargoOfferService.save(co);
		
		assertEquals(0,offerAcceptanceService.findAll().size());
		offerAcceptanceService.initiateOffer("a","b",co);

		List<OfferAcceptance> offerList=offerAcceptanceService.findAll();
		assertEquals(1, offerList.size());
		assertEquals("a", offerList.get(0).getInitiator());
		assertEquals("b", offerList.get(0).getInitiated());
		assertEquals(co.getId(), offerList.get(0).getOfferId());
	}

	@Test
	public void testInitializeOffer2(){
		CargoOffer co=new CargoOffer();
		co.setAddressFrom("Opole");
		cargoOfferService.save(co);
		
		assertEquals(0,offerAcceptanceService.findAll().size());
		offerAcceptanceService.initiateOffer("a","b",co);
		offerAcceptanceService.initiateOffer("a","c",co);

		List<OfferAcceptance> offerList=offerAcceptanceService.findAll();
		assertEquals(1, offerList.size());
		assertEquals("a", offerList.get(0).getInitiator());
		assertEquals("c", offerList.get(0).getInitiated());
		assertEquals(co.getId(), offerList.get(0).getOfferId());
	}

	
	@Test
	public void testInitializeOfferFromBothSides(){
		CargoOffer co=new CargoOffer();
		co.setAddressFrom("Opole");
		cargoOfferService.save(co);
		
		assertEquals(0,offerAcceptanceService.findAll().size());
		assertEquals(0,acceptedOfferService.findAll().size());
		assertEquals(1,cargoOfferService.findAll().size());

		offerAcceptanceService.initiateOffer("a","b",co);

		assertEquals(1,offerAcceptanceService.findAll().size());
		assertEquals(0,acceptedOfferService.findAll().size());
		assertEquals(1,cargoOfferService.findAll().size());

		offerAcceptanceService.initiateOffer("b","a",co);
		
		assertEquals(0,offerAcceptanceService.findAll().size());
		assertEquals(1,acceptedOfferService.findAll().size());
		assertEquals(1,cargoOfferService.findAll().size());
		
		List<AcceptedOffer> acceptedOffers=acceptedOfferService.findAll();
		CargoOffer cargoOffer = cargoOfferService.findById(acceptedOffers.get(0).getOfferId());

		assertEquals(cargoOffer.getAddressFrom(),"Opole");
	}
	
	@Test
	public void testAcceptedOfferHasCorrectuser(){
		CargoOffer co=new CargoOffer();
		co.setAddressFrom("Opole");
		co.setOwner("b");
		cargoOfferService.save(co);

		offerAcceptanceService.initiateOffer("a","b",co);
		offerAcceptanceService.initiateOffer("b","a",co);

		AcceptedOffer acceptedOffer=acceptedOfferService.findAll().get(0);
		assertEquals("a",acceptedOffer.getUserName());
		
	}

	@Test
	public void testAcceptedOfferHasCorrectUserReversed(){
		CargoOffer co=new CargoOffer();
		co.setAddressFrom("Opole");
		co.setOwner("b");
		cargoOfferService.save(co);

		offerAcceptanceService.initiateOffer("b","a",co);
		offerAcceptanceService.initiateOffer("a","b",co);

		AcceptedOffer acceptedOffer=acceptedOfferService.findAll().get(0);
		assertEquals("a",acceptedOffer.getUserName());
		
	}

	
}
