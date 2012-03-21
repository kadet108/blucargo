package com.blucargo.services;

import java.util.List;

import com.blucargo.dao.RegistrationDataDao;
import com.blucargo.model.RegistrationData;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class RegistrationDataService {

	private final RegistrationDataDao registrationDataDao;

	@Inject
	public RegistrationDataService(RegistrationDataDao registrationDataDao) {
		this.registrationDataDao = registrationDataDao;
	}
	public synchronized void save(List<RegistrationData> regData) {
		for (RegistrationData rd : regData) {
			registrationDataDao.saveOrUpdate(rd);
		}
	}

	public synchronized void save(RegistrationData regData) {
		this.registrationDataDao.saveOrUpdate(regData);
	}
	
	public RegistrationData getUserByRegistrationNumber(String number){
		return registrationDataDao.getUserByRegistrationNumber(number);
	}
	
	public boolean checkLoginInTable(String login){
		return registrationDataDao.checkLoginInTable(login);
	}
	
	public void deleteAllByRegId(String string){
		registrationDataDao.deleteAllByRegId(string);
	}
}
