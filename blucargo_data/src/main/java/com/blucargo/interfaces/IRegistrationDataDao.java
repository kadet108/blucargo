package com.blucargo.interfaces;

import com.blucargo.model.RegistrationData;

public interface IRegistrationDataDao {
	public abstract RegistrationData getUserByRegistrationNumber(String number); 

}
