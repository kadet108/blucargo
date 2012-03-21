package com.blucargo.interfaces;

import java.util.List;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.EndedOffer;

public interface IEndedOfferDao {
	//public abstract void removeEndedOfferByCargoOfferAndOwner(CargoOffer co, String owner);
	public abstract List<CargoOffer> getEndedCargoOffersByOwner(String owner);
	public abstract List<EndedOffer> findEndedOfferByOwner(String owner);
	//public abstract EndedOffer findByOfferIdAndUserName(long offerId, String userName);
}
