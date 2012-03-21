package com.blucargo.dao;

import java.util.List;

import javax.persistence.Query;

import com.blucargo.model.SearchCriteria;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class SearchCriteriaDao extends BaseDao<SearchCriteria,Long> {

	protected SearchCriteriaDao() {
		super(SearchCriteria.class);
	}

	public List<SearchCriteria> findByUserName(String userName) {
    	Query q = entityManager.get().createQuery("SELECT criteria FROM SearchCriteria criteria where criteria.userName=:userName ");
		q.setParameter("userName", userName);
		List<SearchCriteria> searchCriteriaList = q.getResultList();
		return searchCriteriaList;
	}

	public void removeById(Long id) {
    	Query q = entityManager.get().createQuery("DELETE FROM SearchCriteria criteria where criteria.id=:id ");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	public List<SearchCriteria> findByUserNameAndCriteriaName(String userName, String name) {
    	Query q = entityManager.get().createQuery("SELECT criteria FROM SearchCriteria criteria where criteria.userName=:userName and criteria.criteriaName=:name");
		q.setParameter("userName", userName);
		q.setParameter("name", name);
		List<SearchCriteria> searchCriteriaList = q.getResultList();
		return searchCriteriaList;
	}

	public void deleteByUserNameAndCriteriaName(String userName, String name) {
    	Query q = entityManager.get().createQuery("delete FROM SearchCriteria criteria where criteria.userName=:userName and criteria.criteriaName=:name");
		q.setParameter("userName", userName);
		q.setParameter("name", name);
		q.executeUpdate();
	}

	public void deleteByUser(String userName) {
    	Query q = entityManager.get().createQuery("delete FROM SearchCriteria criteria where criteria.userName=:userName");
		q.setParameter("userName", userName);
		q.executeUpdate();
	}

}
