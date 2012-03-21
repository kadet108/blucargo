package com.blucargo.services;

import java.util.List;

import com.blucargo.dao.FavouriteOfferDao;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.FavouriteOffer;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class FavouriteOfferService {

	private final FavouriteOfferDao favouriteOfferDao;

	@Inject
	public FavouriteOfferService(FavouriteOfferDao favouriteOfferDao) {
		this.favouriteOfferDao = favouriteOfferDao;
	}
	
	
    public synchronized void saveFavouriteOffers(List<FavouriteOffer> favouriteOffers){
        for(FavouriteOffer c:favouriteOffers)
        {
            saveFavouriteOffer(c);
        }
    }

    private synchronized void saveFavouriteOffer(FavouriteOffer ao) {
    	favouriteOfferDao.saveOrUpdate(ao);
    }

    public synchronized List<FavouriteOffer> findAll(){
    	return favouriteOfferDao.findAll();
    }

    public synchronized FavouriteOffer findById(long id){
    	return favouriteOfferDao.findById(id);
    }
    
    public synchronized void removeFavouriteOfferByCargoOfferAndOwner(CargoOffer co, String owner) {
    	favouriteOfferDao.removeFavouriteOfferByCargoOfferAndOwner(co, owner);
    }

    public synchronized void removeFavouriteOffersByCargoOfferAndOwner(List<CargoOffer> offers, String owner) {
    	for(CargoOffer co:offers){
    		this.removeFavouriteOfferByCargoOfferAndOwner(co, owner);
    	}
    }
    
    public synchronized List<CargoOffer> getFavouriteCargoOffersByOwner(String owner){
    	return favouriteOfferDao.getFavouriteCargoOffersByOwner(owner);
    }
}
