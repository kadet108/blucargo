package com.blucargo.dao;

import java.util.List;

import javax.persistence.Query;

import com.blucargo.model.OfferAcceptance;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class OfferAcceptanceDao extends BaseDao<OfferAcceptance,Long>{

	protected OfferAcceptanceDao() {
		super(OfferAcceptance.class);
	}

	public void deleteByInitiator(String string) {
        Query query = entityManager.get().createQuery("DELETE FROM OfferAcceptance offer where offer.initiator=:initiator");
        query.setParameter("initiator", string);
        query.executeUpdate();
	}

	public OfferAcceptance find(String initiator, String initiated, long cargoOfferId) {

		String queryString="SELECT e FROM "+persistentClass.getName()+" e where e.initiator=:initiator and e.initiated=:initiated and e.offerId=:offerId";
		Query query = entityManager.get().createQuery(queryString);
        query.setParameter("initiator", initiator);
        query.setParameter("initiated", initiated);
        query.setParameter("offerId", cargoOfferId);
        List list=query.getResultList();
        
        if (list.size()>0){
        	return (OfferAcceptance)list.get(0);
        }
        return null;
	}

	
	public void save(OfferAcceptance offer) {
        Query query = entityManager.get().createQuery("DELETE FROM OfferAcceptance offer where offer.initiator=:initiator and offer.offerId=:offerId");
        query.setParameter("initiator", offer.getInitiator());
        query.setParameter("offerId", offer.getOfferId());
        query.executeUpdate();
        this.saveOrUpdate(offer);
		
	}

}
