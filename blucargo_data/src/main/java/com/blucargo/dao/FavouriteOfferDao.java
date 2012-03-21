package com.blucargo.dao;

import java.util.List;

import javax.persistence.Query;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.FavouriteOffer;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class FavouriteOfferDao extends BaseDao<FavouriteOffer,Long>{

	protected FavouriteOfferDao() {
		super(FavouriteOffer.class);
	}
	
    public synchronized void removeFavouriteOfferByCargoOfferAndOwner(CargoOffer co, String owner) {
    	Query q = entityManager.get().createQuery("delete from FavouriteOffer ao where ao.offerId=:cargoOfferId"+
    	" and ao.userName=:owner");
    	q.setParameter("cargoOfferId", co.getId());
    	q.setParameter("owner", owner);
    	@SuppressWarnings("unused")
		int result=q.executeUpdate();
    }
    
    @SuppressWarnings("unchecked")
	public synchronized List<CargoOffer> getFavouriteCargoOffersByOwner(String owner){
    	Query q = entityManager.get().createQuery("SELECT co FROM CargoOffer co where co.id in " +
    			"(select fo.offerId from FavouriteOffer fo where fo.userName=:owner)");
    	q.setParameter("owner", owner);
    	List<CargoOffer> offerList = q.getResultList();
    	return offerList;
    }



}
