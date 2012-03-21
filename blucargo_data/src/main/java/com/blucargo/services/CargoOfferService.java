package com.blucargo.services;

import java.util.List;

import com.blucargo.dao.CargoOfferDao;
import com.blucargo.interfaces.ICargoOfferDao;
import com.blucargo.model.CargoOffer;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class CargoOfferService {

	private final CargoOfferDao cargoOfferDao;

	@Inject
	public CargoOfferService(CargoOfferDao cargoOfferDao) {
		this.cargoOfferDao = cargoOfferDao;
	}

	public synchronized void save(List<CargoOffer> offerList) {
		for (CargoOffer co : offerList) {
			cargoOfferDao.saveOrUpdate(co);
		}
	}

	public synchronized void save(CargoOffer co) {
			cargoOfferDao.saveOrUpdate(co);
	}

	public synchronized void removeCargoOffers(List<CargoOffer> cargoOffers) {
		for (CargoOffer co : cargoOffers) {
			this.removeCargoOffer(co);
		}
	}

	public synchronized void removeCargoOffer(CargoOffer cargoOffer) {
		cargoOfferDao.delete(cargoOffer);

	}

	public synchronized List<CargoOffer> findAll() {
		return cargoOfferDao.findAll();
	}

	public synchronized List<CargoOffer> findAllThatAreNotAcceptedNorDeleted(){
		return cargoOfferDao.findAllThatAreNotAcceptedNorEnded();
	}
	
    public synchronized CargoOffer findById(long id){
    	return cargoOfferDao.findById(id);
    }
    
    public synchronized CargoOffer findOfferById(long id){
    	return cargoOfferDao.findOfferById(id);
    }
    
    public ICargoOfferDao getCargoOfferDao(){
    	return this.cargoOfferDao;
    }
}
