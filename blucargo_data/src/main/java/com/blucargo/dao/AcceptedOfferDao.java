package com.blucargo.dao;

import java.util.List;

import javax.persistence.Query;

import com.blucargo.model.AcceptedOffer;
import com.blucargo.model.CargoOffer;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class AcceptedOfferDao extends BaseDao<AcceptedOffer,Long>{

	protected AcceptedOfferDao() {
		super(AcceptedOffer.class);
	}
	
    public synchronized void removeAcceptedOfferByCargoOfferAndOwner(CargoOffer co, String owner) {
    	Query q = entityManager.get().createQuery("delete from AcceptedOffer ao where ao.offerId=:cargoOfferId"+
    	" and ao.userName=:owner");
    	q.setParameter("cargoOfferId", co.getId());
    	q.setParameter("owner", owner);
    	q.executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
	public synchronized List<CargoOffer> getAcceptedCargoOffersByOwner(String owner){
    	Query q = entityManager.get().createQuery("SELECT co FROM CargoOffer co where co.id in " +
    			"(select ao.offerId from AcceptedOffer ao where ao.userName=:owner)");
    	q.setParameter("owner", owner);
    	List<CargoOffer> offerList = q.getResultList();
    	return offerList;
    }

	public AcceptedOffer getAcceptedOfferByCargoOfferIdAndUserName(Long cargoOfferId, String userName) {
		Query q = entityManager.get().createQuery("SELECT ao FROM AcceptedOffer ao where ao.offerId=:cargoOfferId and ao.userName=:userName");
		q.setParameter("cargoOfferId", cargoOfferId);
		q.setParameter("userName", userName);
		List<AcceptedOffer> acceptedOffers= q.getResultList();
		
		if(acceptedOffers.size()>0){
			return acceptedOffers.get(0);
		}
		
		return null;
	}
	
    public void delete(AcceptedOffer entity) {
        entityManager.get().remove(entityManager.get().merge(entity));
    }

    
}
