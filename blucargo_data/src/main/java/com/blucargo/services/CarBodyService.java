package com.blucargo.services;

import java.util.List;

import com.blucargo.dao.CarBodyDao;
import com.blucargo.model.CarBody;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class CarBodyService {
	
	private final CarBodyDao bodyDao;

	@Inject
	public CarBodyService(CarBodyDao bodyDao){
		this.bodyDao=bodyDao;
	}
	
    public synchronized void saveBodies(List<CarBody> bodies){
//    	CargoConnectionManager.getInstance().getEntityManager().getTransaction().begin();
        for(CarBody b:bodies)
        {
            saveBody(b);
        }
//    	CargoConnectionManager.getInstance().getEntityManager().getTransaction().commit();

    }

    private synchronized void saveBody(CarBody b) {
    	bodyDao.saveOrUpdate(b);
    }

    public synchronized List<CarBody> findAll(){
    	return bodyDao.findAll();
    }
    
    public synchronized CarBody findById(long id){
    	return bodyDao.findById(id);
    }
}
