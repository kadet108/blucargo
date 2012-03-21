package com.blucargo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.dao.AcceptedOfferDao;
import com.blucargo.dao.CargoOfferDao;
import com.blucargo.dao.EndedOfferDao;
import com.blucargo.model.AcceptedOffer;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.EndedOffer;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class CargoOfferDaoDbTest extends TransactionRollbackTestBase {

	@Inject
	CargoOfferDao cargoOfferDao;
	@Inject
	AcceptedOfferDao acceptedOfferDao;
	@Inject
	EndedOfferDao endedOfferDao;

	@Test
	public void testSaveCargoOffer() {
		CargoOffer cargoOffer = new CargoOffer();
		cargoOffer.setAddressFrom("Adr");
		cargoOfferDao.saveOrUpdate(cargoOffer);

		getEntityManager().flush();

		CargoOffer cargoOffer2 = cargoOfferDao.findById(cargoOffer.getId());
		assertNotNull(cargoOffer2);
		assertEquals(cargoOffer2.getAddressFrom(), "Adr");
	}

	@Test
	public void testFindAll() {
		CargoOffer cargoOffer = new CargoOffer();
		cargoOffer.setAddressFrom("Adr");
		cargoOfferDao.saveOrUpdate(cargoOffer);

		getEntityManager().flush();

		List<CargoOffer> list = cargoOfferDao.findAll();
		assertTrue(list.size()>0);
	}

	@Test
	public void testFindAllThatAreNotAcceptedNorEnded() {
		CargoOffer cargoOffer1 = new CargoOffer();
		cargoOffer1.setAddressFrom("111");
		CargoOffer cargoOffer2 = new CargoOffer();
		cargoOffer2.setAddressFrom("222");
		CargoOffer cargoOffer3 = new CargoOffer();
		cargoOffer3.setAddressFrom("333");
		CargoOffer cargoOffer4 = new CargoOffer();
		cargoOffer4.setAddressFrom("444");
		
		cargoOfferDao.saveOrUpdate(cargoOffer1);
		cargoOfferDao.saveOrUpdate(cargoOffer2);
		cargoOfferDao.saveOrUpdate(cargoOffer3);
		cargoOfferDao.saveOrUpdate(cargoOffer4);

		getEntityManager().flush();

		assertEquals(4,cargoOfferDao.findAllThatAreNotAcceptedNorEnded().size());
		
		AcceptedOffer acceptedOffer1=new AcceptedOffer();
		acceptedOffer1.setOfferId(cargoOffer1.getId());
		AcceptedOffer acceptedOffer2=new AcceptedOffer();
		acceptedOffer2.setOfferId(cargoOffer2.getId());

		acceptedOfferDao.saveOrUpdate(acceptedOffer1);
		acceptedOfferDao.saveOrUpdate(acceptedOffer2);

		getEntityManager().flush();
		
		assertEquals(2,cargoOfferDao.findAllThatAreNotAcceptedNorEnded().size());

		EndedOffer endedOffer3 = new EndedOffer();
		endedOffer3.setOfferId(cargoOffer3.getId());
		
		endedOfferDao.saveOrUpdate(endedOffer3);
	
		getEntityManager().flush();
	
		assertEquals(1,cargoOfferDao.findAllThatAreNotAcceptedNorEnded().size());
	}

	
	
}
