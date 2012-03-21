package com.blucargo.dao;

import javax.persistence.Query;

import com.blucargo.interfaces.IRegistrationDataDao;
import com.blucargo.model.RegistrationData;

public class RegistrationDataDao extends BaseDao<RegistrationData,Long> implements IRegistrationDataDao {

	protected RegistrationDataDao() {
		super(RegistrationData.class);
	}
	
	public RegistrationData getUserByRegistrationNumber(String number) {
    	String queryString = "SELECT c from RegistrationData c where c.regId=:regNumber";
		Query query = entityManager.get().createQuery(queryString);
		query.setParameter("regNumber", number);
		return (RegistrationData) query.getSingleResult();
	}	

	public boolean checkLoginInTable(String login) {
    	String queryString = "SELECT c from RegistrationData c where c.userName=:login";
		Query query = entityManager.get().createQuery(queryString);
		query.setParameter("login", login);
		if( query.getResultList().size() > 0 )
			return true;
		else
			return false;
	}
	
	public void deleteAllByRegId(String string) {
        Query query = entityManager.get().createQuery("DELETE FROM RegistrationData data where data.regId=:id");
        query.setParameter("id", string);
        query.executeUpdate();
	}

}
