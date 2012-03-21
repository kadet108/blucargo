package org.jivesoftware.sparkimpl.plugin.blucargo.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blucargo.model.City;

public class CargoLocationManager {

	private Map<String, String> countriesMap;
	private Map<String, List<String>> citiesMap;
	private static CargoLocationManager instance = null;
	static ClassLoader cl = CargoLocationManager.class.getClassLoader();
    private String dataFolderLocation;
	
	protected CargoLocationManager() {
		File cf = new File("");
		this.dataFolderLocation = cf.getAbsolutePath() + File.separator + "data" + File.separator;
		citiesMap = new HashMap<String, List<String>>();
	}

	public static CargoLocationManager getInstance() {
		if (instance == null) {
			instance = new CargoLocationManager();
		}

		return instance;
	}

	public Map<String, String> getCountries() {
		if (getCountriesMap() != null) {
			return getCountriesMap();
		}

		Map<String, String> countriesMapTemp = this.getCountriesFromFile();
        setCountriesMap(countriesMapTemp);
		
//		if (countriesMapTemp == null) {
//			countriesMapTemp = this.getCountriesFromDatabase();
//			this.saveCountriesToFile(countriesMapTemp);
//		}

///		this.setCountriesMap(countriesMapTemp);
		return countriesMapTemp;
	}

	public List<String> getCities(String country) {

		String verifiedCountry = "";

		if (getCountriesMap() == null) {
			getCountries();
		}

		if (getCountriesMap().containsKey(country)) {
			verifiedCountry = getCountriesMap().get(country);
		} else if (getCountriesMap().containsValue(country)) {
			verifiedCountry = country;
		}

		List<String> cities = getCitiesMap().get(verifiedCountry);

		//Gettig cities from file.
//		if (cities == null) {
//            Map<String, List<City>> citiesMapTemp = CargoLocationManager.getInstance().getCitiesFromFile();        	
//            if (citiesMapTemp == null) {
//            	//OK
//            } else {
//            	setCitiesMap(citiesMapTemp);
//            	cities = getCitiesMap().get(verifiedCountry);            	
//            }
//		}
		
		//Getting cities from database.
//		if (cities == null) {
//			List<City> citiesListTemp = CargoDataManager.getInstance().getCitiesByCountry(verifiedCountry);
//			getCitiesMap().put(verifiedCountry, citiesListTemp);
//
//			CargoLazyLoadingManager.getInstance().lazyLoadCities();
//			cities = getCitiesMap().get(verifiedCountry);
//		}

		return cities;
	}

//	protected Map<String, String> getCountriesFromDatabase() {
//		if (getCountriesMap() != null) {
//			return getCountriesMap();
//		}
//
//		setCountriesMap(new TreeMap<String, String>());
//		List<Country> countries = CargoDataManager.getInstance().getCountries();
//
//		for (int i = 0; i < countries.size(); i++) {
//			Country c = countries.get(i);
//			getCountriesMap().put(c.getName(), c.getIso_3166_1_alfa_2());
//		}
//
//		return getCountriesMap();
//	}

	protected Map<String, String> getCountriesFromFile() {
		
		//First, try to get it from a jar.
		InputStream countriesInputStream = cl.getResourceAsStream("countries.ser");

		//If it does not exist, get it from a file.
		//Next read from a file on a filesystem.
		if (countriesInputStream == null){
			try {
				countriesInputStream = new FileInputStream(new File(dataFolderLocation + "countries.ser"));
			} catch (FileNotFoundException e) {
				return null;
			}
		}
		
		Map<String, String> countriesMap = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(countriesInputStream);
			countriesMap = (Map<String, String>) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return countriesMap;

	}

	protected void saveCountriesToFile(Map<String, String> countriesMap) {
		File cf = new File("");

		String filename = dataFolderLocation + "countries.ser";
		String dataFilename = cf.getAbsolutePath() + File.separator + "data";

		File f = new File(filename);
		File data = new File(dataFilename);

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

	protected Map<String, List<String>> getCitiesFromFile() {
		//First we will try to read from a jar.		
		InputStream citiesInputStream = cl.getResourceAsStream("cities.ser");
		
		//Next read from a file on a filesystem.
		if (citiesInputStream == null){
			try {
				citiesInputStream = new FileInputStream(new File(dataFolderLocation + "cities.ser"));
			} catch (FileNotFoundException e) {
				return null;
			}
		}
		
		
		Map<String, List<String>> citiesMapTemp = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(citiesInputStream);
			citiesMapTemp = (Map<String, List<String>>) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return citiesMapTemp;
	}

//	protected Map<String, List<City>> getCitiesFromDatabase(
//			Map<String, String> countriesMap) {
//		HashMap<String, List<City>> citiesMapTemp = new HashMap<String, List<City>>();
//
//		for (String c : countriesMap.values()) {
//			List<City> citiesTemp = citiesMapTemp.get(c);
//
//			if (citiesTemp == null) {
//				
//				List<City> citiesListTemp = CargoDataManager.getInstance().getCitiesByCountry(c);
//				citiesMapTemp.put(c, citiesListTemp);
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//
//		}
//		return citiesMapTemp;
//	}

	protected void saveCitiesToFile(Map<String, List<City>> citiesMapTemp) {
		String filename = dataFolderLocation + "cities.ser";
		String dataFilename = dataFolderLocation;

		File f = new File(filename);
		File data = new File(dataFilename);

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

	protected Map<String, String> getCountriesMap() {
		return countriesMap;
	}

	protected void setCountriesMap(Map<String, String> countriesMap) {
		this.countriesMap = countriesMap;
	}

	protected Map<String, List<String>> getCitiesMap() {
		return citiesMap;
	}

	protected void setCitiesMap(Map<String, List<String>> citiesMap) {
		this.citiesMap = citiesMap;
	}
}
