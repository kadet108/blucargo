package com.blucargo.services;

import java.util.List;

import com.blucargo.dao.EndedOfferDao;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.EndedOffer;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class EndedOfferService {

	private final EndedOfferDao endedOfferDao;

	@Inject
	public EndedOfferService(EndedOfferDao endedOfferDao) {
		this.endedOfferDao = endedOfferDao;
	}
	
    public synchronized void saveEndedOffers(List<EndedOffer> endedOffers){
    	for(EndedOffer c:endedOffers)
        {
            save(c);
        }
    }

    public synchronized void save(EndedOffer ao) {
    	EndedOffer endedOffer = endedOfferDao.findByOfferIdAndUserName(ao.getOfferId(),ao.getUserName());

    	if(endedOffer==null){
    		endedOfferDao.saveOrUpdate(ao);
    	}
    }

    public synchronized void removeEndedOffer(EndedOffer ao) {
    	ao.setVisible(0);
    	endedOfferDao.saveOrUpdate(ao);
    }

    public synchronized void removeEndedOfferByCargoOfferAndOwner(CargoOffer co, String owner) {
    	endedOfferDao.removeEndedOfferByCargoOfferAndOwner(co, owner);
    }

    public synchronized void removeEndedOffersByCargoOfferAndOwner(List<CargoOffer> offers, String owner) {
    	for(CargoOffer co:offers){
    		this.removeEndedOfferByCargoOfferAndOwner(co, owner);
    	}
    }

    public synchronized List<EndedOffer> findAll(){
    	return endedOfferDao.findAll();
    }

    public synchronized EndedOffer findById(long id){
    	return endedOfferDao.findById(id);
    }

    public synchronized EndedOffer findByOfferIdAndUserName(long offerId,String userName){
    	return endedOfferDao.findByOfferIdAndUserName(offerId,userName);
    }
    
    public synchronized List<CargoOffer> getEndedCargoOffersByOwner(String owner){
    	return endedOfferDao.getEndedCargoOffersByOwner(owner);
    }
    
    public List<EndedOffer> findEndedOfferByOwner(String owner){
    	return this.endedOfferDao.findEndedOfferByOwner(owner);
    }

}
