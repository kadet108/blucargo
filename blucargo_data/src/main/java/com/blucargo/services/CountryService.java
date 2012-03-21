package com.blucargo.services;

import java.util.List;

import com.blucargo.dao.CountryDao;
import com.blucargo.model.Country;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class CountryService {

	private final CountryDao countryDao;
	@Inject
	public CountryService(CountryDao countryDao) {
		this.countryDao = countryDao;
	}
	
	public synchronized void saveCountries(List<Country> countries){
        for(Country c:countries)
        {
            saveCountry(c);
        }
    }

    public synchronized void saveCountry(Country c) {
    	countryDao.saveOrUpdate(c);
    }

    public synchronized List<Country> findAll(){
    	return countryDao.findAll();
    }
    
    public synchronized List<Country> findAllCountry(){
    	return countryDao.findAllCountry();
    }
    
    public synchronized Country findById(long id){
    	return countryDao.findById(id);
    }
	
	
}
