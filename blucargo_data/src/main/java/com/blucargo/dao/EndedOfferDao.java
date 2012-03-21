package com.blucargo.dao;

import java.util.List;

import javax.persistence.Query;

import com.blucargo.interfaces.IEndedOfferDao;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.EndedOffer;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class EndedOfferDao extends BaseDao<EndedOffer,Long> implements IEndedOfferDao{

	protected EndedOfferDao() {
		super(EndedOffer.class);
	}
	
    public synchronized void removeEndedOfferByCargoOfferAndOwner(CargoOffer co, String owner) {
    	Query q = entityManager.get().createQuery("UPDATE EndedOffer o set o.visible=:visible where o.offerId=:cargoOfferId"+
    	" and o.userName=:owner");
    	q.setParameter("cargoOfferId", co.getId());
    	q.setParameter("owner", owner);
    	q.setParameter("visible", false);
    	q.executeUpdate();
    }
    
	@SuppressWarnings("unchecked")
	public synchronized List<CargoOffer> getEndedCargoOffersByOwner(String owner){
    	Query q = entityManager.get().createQuery("SELECT co FROM CargoOffer co where co.id in " +
    			"(select ao.offerId from EndedOffer ao where ao.userName=:owner and ao.visible=1)");
    	q.setParameter("owner", owner);
    	List<CargoOffer> offerList = q.getResultList();
    	return offerList;
    }
	
	@SuppressWarnings("unchecked")
	public List<EndedOffer> findEndedOfferByOwner(String owner){
    	Query q = entityManager.get().createQuery("SELECT ef FROM EndedOffer ef where ef.userName=:owner");
    	q.setParameter("owner", owner);
    	List<EndedOffer> offerList = (List<EndedOffer> )q.getResultList();
    	return offerList;
    }

	public EndedOffer findByOfferIdAndUserName(long offerId, String userName) {
		Query q = entityManager.get().createQuery("SELECT eo from EndedOffer eo WHERE eo.offerId=:offerId and eo.userName=:userName");
		q.setParameter("offerId", offerId);
		q.setParameter("userName", userName);
		
		List<EndedOffer> endedOffer = q.getResultList();
		
		if(endedOffer.size()>0){
			return endedOffer.get(0);
		}
		
		return null;
	}

}
