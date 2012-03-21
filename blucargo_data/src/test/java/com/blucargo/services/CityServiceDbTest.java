package com.blucargo.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.model.City;
import com.blucargo.services.CityService;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class CityServiceDbTest extends TransactionRollbackTestBase{

	@Inject
    CityService cityService;
	
	@Before
	public void setUp(){
		
	}

	@Test
	public void testGetCitiesByCountry(){
		City c1=new City();
		c1.setCity("aaaa");
		c1.setCountry("pl");
		
		City c2=new City();
		c2.setCity("bbbb");
		c2.setCountry("pl");
		
		City c3=new City();
		c3.setCity("cccc");
		c3.setCountry("be");
		
		cityService.saveCity(c1);
		cityService.saveCity(c2);
		cityService.saveCity(c3);
		
		List<City> cities = cityService.findAll();
		
		assertEquals(cityService.findCitiesByCountry("pl").size(),2);
		
	}
	
}
