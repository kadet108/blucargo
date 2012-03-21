package com.blucargo.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.model.Country;
import com.blucargo.services.CountryService;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class CountryServiceDbTest extends TransactionRollbackTestBase{

	@Inject
    CountryService countryService;
	
	@Before
	public void setUp(){
		
	}
	
	@Test
	public void testGetCountries(){
		Country country=new Country();
		country.setIso_3166_1_alfa_2("aaa");
		
		countryService.saveCountry(country);
		assertTrue(countryService.findAll().size()>0);
		
	}

	
}
