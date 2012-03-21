package com.blucargo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.dao.CargoOfferDao;
import com.blucargo.dao.FavouriteOfferDao;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.FavouriteOffer;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class FavouriteOfferDaoDbTest extends TransactionRollbackTestBase {

	@Inject
	FavouriteOfferDao favouriteOfferDao;

	@Inject
	CargoOfferDao cargoOfferDao;

	private CargoOffer co;
	private FavouriteOffer ao;

	@Before
	public void setUp() {
		co = new CargoOffer();
		co.setOwner("a");
		cargoOfferDao.saveOrUpdate(co);

		ao = new FavouriteOffer();
		ao.setOfferId(co.getId());
		ao.setUserName("b");
		favouriteOfferDao.saveOrUpdate(ao);
	}

	@Test
	public void testGetFavouriteCargoOffersByOwner() {
		List<FavouriteOffer> favouriteOffers = favouriteOfferDao.findAll();
		assertTrue(favouriteOffers.size() > 0);
		assertEquals(favouriteOffers.get(0).getUserName(), "b");
		assertEquals(favouriteOffers.get(0).getOfferId(), co.getId());
	}

	@Test
	public void testRemoveAcceptedCargoOffersByOwner() {
		favouriteOfferDao.removeFavouriteOfferByCargoOfferAndOwner(co, "b");
		List<FavouriteOffer> favouriteOffers = favouriteOfferDao.findAll();
		assertTrue(favouriteOffers.size() == 0);
	}

}
