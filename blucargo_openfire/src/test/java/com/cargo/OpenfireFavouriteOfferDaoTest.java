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
import com.blucargo.dao.FavouriteOfferDao;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.FavouriteOffer;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class OpenfireFavouriteOfferDaoTest {

	@Inject
	CargoOfferDao cargoOfferDao;

	@Inject
	FavouriteOfferDao favouriteOfferDao;
	
	public OpenfireFavouriteOfferDaoTest() {
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
		
		FavouriteOffer favouriteOffer = new FavouriteOffer();
		favouriteOffer.setOfferId(co.getId());
		favouriteOffer.setUserName("a");
		List<FavouriteOffer> favouriteOffers = new ArrayList<FavouriteOffer>();
		favouriteOffers.add(favouriteOffer);
		
		favouriteOfferDao.saveOrUpdate(favouriteOffer);
//		favouriteOfferDao.getSessionProvider().get().flush();
		
		List<FavouriteOffer> favouriteOffers2=favouriteOfferDao.findAll();
		assertTrue(favouriteOffers2.size()>0);
		
		
	}

//	@Test
//	public void LoadTest() {
//		CargoOffer co = new CargoOffer();
//		co.setAddressTo("sdfsdf");
//		co.setAddressFrom("sdfsdf");
//		co.setBody("body");
//		ArrayList<CargoOffer> cargoOffers=new ArrayList<CargoOffer>();
//		cargoOffers.add(co);
//		cargoOfferService.saveCargoOffers(cargoOffers);
//		
//		List<CargoOffer> cargoOfferList = cargoOfferService.getCargoOffers();
//		assertTrue(cargoOfferList.size() > 0);
//	}

}