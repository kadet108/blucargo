package com.blucargo.model.init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.blucargo.model.City;
import com.blucargo.model.Country;
import com.blucargo.persistence.MainPersistenceModule;
import com.blucargo.services.CityService;
import com.blucargo.services.CountryService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GetCountriesAndCitiesFromDatabaseAndSaveThemToFile {

	private static String dataFolderLocation;
	
	public static void main(String [] args){
		File cf = new File("");
		dataFolderLocation = cf.getAbsolutePath() + File.separator + "src" + File.separator+ "main" + File.separator+ "data" + File.separator;

		
		Injector injector = Guice.createInjector(new MainPersistenceModule());

		CountryService countryService = injector.getInstance(CountryService.class);
		CityService cityService = injector.getInstance(CityService.class);
		
		List<Country> countries = countryService.findAll();
	    Map<String, String> countriesMap = new TreeMap<String, String>();
		for(Country country: countries){
			countriesMap.put(country.getName(), country.getIso_3166_1_alfa_2());
		}
		
		Map<String, List<String>> citiesMap= new HashMap<String, List<String>>();
		
		for(Country country: countries) {
//			List<City> citiesList = cityService.findCitiesByCountry(country.getIso_3166_1_alfa_2());
			List<String> citiesList = cityService.findCityNamesByCountry(country.getIso_3166_1_alfa_2());
			citiesMap.put(country.getIso_3166_1_alfa_2(), citiesList);
			
		}
		
		saveCountriesToFile(countriesMap);
		saveCitiesToFile(citiesMap);
		
	}
	
	protected static void saveCitiesToFile(Map<String, List<String>> citiesMapTemp) {
		String filename = dataFolderLocation + "cities.ser";

		File f = new File(filename);
		File data = new File(dataFolderLocation);

		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			if (!data.exists()) {
				data.mkdir();
			}
			f.createNewFile();
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(citiesMapTemp);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	protected static void saveCountriesToFile(Map<String, String> countriesMap) {
		String filename = dataFolderLocation + "countries.ser";

		File f = new File(filename);
		File data = new File(dataFolderLocation);

		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			if (!data.exists()) {
				data.mkdir();
			}
			f.createNewFile();
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(countriesMap);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
}
