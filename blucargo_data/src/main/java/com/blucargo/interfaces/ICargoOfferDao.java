package com.blucargo.interfaces;

import com.blucargo.model.CargoOffer;

public interface ICargoOfferDao {
	//public List<CargoOffer> findAllThatAreNotAcceptedNorEnded();
	public abstract CargoOffer findOfferById(Long id);
}