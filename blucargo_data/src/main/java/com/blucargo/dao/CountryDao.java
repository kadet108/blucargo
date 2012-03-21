package com.blucargo.dao;

import java.util.List;

import javax.persistence.Query;

import com.blucargo.interfaces.ICountryDao;
import com.blucargo.model.Country;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class CountryDao extends BaseDao<Country,Long> implements ICountryDao{

	protected CountryDao() {
		super(Country.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Country> findAllCountry() {
    	String queryString = "SELECT c from Country c";
		Query query = entityManager.get().createQuery(queryString);
		//query.setParameter("offerId", id);
		return (List<Country>) query.getResultList();
	}


}
