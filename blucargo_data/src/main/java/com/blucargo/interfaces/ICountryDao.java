package com.blucargo.interfaces;

import java.util.List;

import com.blucargo.model.Country;

public interface ICountryDao {
	public List<Country> findAllCountry();
}
