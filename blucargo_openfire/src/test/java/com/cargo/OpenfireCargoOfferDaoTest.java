package com.cargo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.dao.CargoOfferDao;
import com.blucargo.model.CargoOffer;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class OpenfireCargoOfferDaoTest {

	@Inject
	CargoOfferDao cargoOfferDao;
	
	public OpenfireCargoOfferDaoTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void SaveLoadTest() {
		CargoOffer co = new CargoOffer();
		co.setAddressTo("sdfsdf");
		co.setAddressFrom("sdfsdf");
		co.setBody("body");
		List<CargoOffer> cargoOffers=new ArrayList<CargoOffer>();
		cargoOffers.add(co); 
		cargoOfferDao.saveOrUpdate(co);
		
		List<CargoOffer> cargoOffers2=cargoOfferDao.findAll();
		assertTrue(cargoOffers2.size()>0);
		
		
	}
}