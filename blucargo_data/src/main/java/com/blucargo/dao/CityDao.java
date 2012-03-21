package com.blucargo.dao;

import java.util.List;

import javax.persistence.Query;

import com.blucargo.model.City;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class CityDao extends BaseDao<City,Long> {

	protected CityDao() {
		super(City.class);
	}

	public List<City> findCitiesByCountry(String country) {
    	String queryString = "SELECT c from City c where c.country=:country";
    	Query query = entityManager.get().createQuery(queryString);
    	query.setParameter("country", country);
		List<City> resultList = (List<City>) query.getResultList();
		return resultList;
	}

	public List<String> findCityNamesByCountry(String country) {
    	String queryString = "SELECT c.city from City c where c.country=:country";
    	Query query = entityManager.get().createQuery(queryString);
    	query.setParameter("country", country);
		List<String> resultList = (List<String>) query.getResultList();
		return resultList;
	}

	
}
