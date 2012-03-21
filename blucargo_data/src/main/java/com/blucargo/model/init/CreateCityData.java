package com.blucargo.model.init;

import java.util.ArrayList;

import com.blucargo.model.City;


public class CreateCityData {

    public CreateCityData()
    {
        
    }

    public ArrayList<City> Create()
    {
            ArrayList<City> cities=new ArrayList<City>();
            cities.add(new City(1,"AF","AFGANISTAN","Afganistan","AFG",0.0,0.0,0.0,0.0));

            return cities;
    }

}
