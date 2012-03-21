package com.blucargo.services;

import java.util.List;

import com.blucargo.dao.SearchCriteriaDao;
import com.blucargo.model.SearchCriteria;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class SearchCriteriaService {

	private final SearchCriteriaDao searchCriteriaDao;

	@Inject
	public SearchCriteriaService(SearchCriteriaDao searchCriteriaDao) {
		this.searchCriteriaDao = searchCriteriaDao;
	}
	
	public synchronized void save(List<SearchCriteria> searchCriterias){
        for(SearchCriteria s:searchCriterias)
        {
            save(s);
        }
    }

    public synchronized void save(SearchCriteria c) {
    	searchCriteriaDao.saveOrUpdate(c);
    }

    public synchronized List<SearchCriteria> findAll(){
    	return searchCriteriaDao.findAll();
    }
    
    public synchronized SearchCriteria findById(long id){
    	return searchCriteriaDao.findById(id);
    }

	public List<SearchCriteria> findByUserName(String string) {
		return searchCriteriaDao.findByUserName(string);
	}

	public void removeById(Long id) {
		searchCriteriaDao.removeById(id);
	}

	public List<SearchCriteria> findByUserNameAndCriteriaName(String userName, String name) {
		return searchCriteriaDao.findByUserNameAndCriteriaName(userName, name);
	}

	public void deleteByUserNameAndCriteriaName(String userName, String name) {
		searchCriteriaDao.deleteByUserNameAndCriteriaName(userName, name);
	}

	public void deleteByUser(String string) {
		searchCriteriaDao.deleteByUser(string);
	}
	
	
}
