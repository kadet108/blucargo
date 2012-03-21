package org.jivesoftware.sparkimpl.plugin.blucargo.manager;

import java.util.List;
import java.util.Map;

import com.blucargo.model.City;


public class CargoLazyLoadingManager {

    private boolean loadingCities;
    private boolean loadingCountries;
    private static CargoLazyLoadingManager instance = null;

    protected CargoLazyLoadingManager() {
    }

    public static CargoLazyLoadingManager getInstance() {
        if (instance == null) {
            instance = new CargoLazyLoadingManager();
        }
        return instance;
    }

//    public void lazyLoadCountries() {
//        if (loadingCountries) {
//            return;
//        }
//
//        new Thread(new Runnable() {
//
//            public void run() {
//                loadingCountries=true;
//                Map<String, String> countriesMapTemp = CargoLocationManager.getInstance().getCountriesFromFile();
//                CargoLocationManager.getInstance().setCountriesMap(countriesMapTemp);
//                loadingCountries=false;
//            }
//        }).start();
//
//    }
//
//    public void lazyLoadCities() {
//
//        if (loadingCities) {
//            return;
//        }
//
//        new Thread(new Runnable() {
//
//            public void run() {
//                loadingCities = true;
//                Map<String, List<City>> citiesMapTemp = CargoLocationManager.getInstance().getCitiesFromFile();
//
//                CargoLocationManager.getInstance().setCitiesMap(citiesMapTemp);
//                loadingCities = false;
//            }
//        }).start();
//    }
    
    public void lazyLoadCountries() {
//        if (loadingCountries) {
//            return;
//        }
//
//        new Thread(new Runnable() {
//
//            public void run() {
//                loadingCountries=true;
                Map<String, String> countriesMapTemp = CargoLocationManager.getInstance().getCountriesFromFile();
                CargoLocationManager.getInstance().setCountriesMap(countriesMapTemp);
                loadingCountries=false;
//            }
//        }).start();

    }

    public void lazyLoadCities() {

//        if (loadingCities) {
//            return;
//        }
//
//        new Thread(new Runnable() {
//
//            public void run() {
//                loadingCities = true;
                Map<String, List<String>> citiesMapTemp = CargoLocationManager.getInstance().getCitiesFromFile();

                CargoLocationManager.getInstance().setCitiesMap(citiesMapTemp);
//                loadingCities = false;
//            }
//        }).start();
    }

}
