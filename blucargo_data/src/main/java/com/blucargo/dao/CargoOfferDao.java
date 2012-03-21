package com.blucargo.dao;

import java.util.List;

import javax.persistence.Query;

import com.blucargo.interfaces.ICargoOfferDao;
import com.blucargo.model.CargoOffer;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class CargoOfferDao extends BaseDao<CargoOffer,Long> implements ICargoOfferDao{

	protected CargoOfferDao() {
		super(CargoOffer.class);
	}
	
    @SuppressWarnings("unchecked")
    public List<CargoOffer> findAllThatAreNotAcceptedNorEnded() {
    	String queryString = "SELECT c from CargoOffer c where c.id not in" +
    			"(select ao.offerId from AcceptedOffer ao) and c.id not in" +
    			"(select eo.offerId from EndedOffer eo)";
        Query query = entityManager.get().createQuery(queryString);
        return (List<CargoOffer>) query.getResultList();
    }

	public CargoOffer findOfferById(Long id) {
		String queryString = "SELECT c from CargoOffer c where c.id=:offerId";
		Query query = entityManager.get().createQuery(queryString);
		query.setParameter("offerId", id);
		return (CargoOffer) query.getSingleResult();
	}
}
