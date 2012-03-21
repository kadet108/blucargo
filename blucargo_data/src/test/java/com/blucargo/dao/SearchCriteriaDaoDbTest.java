package com.blucargo.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.dao.SearchCriteriaDao;
import com.blucargo.model.SearchCriteria;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class SearchCriteriaDaoDbTest extends TransactionRollbackTestBase{

	@Inject
    SearchCriteriaDao criteriaDao;
	
	@Before
	public void setUp(){
	}

	@Test
	public void testSaveSearchCriteria(){
		
		SearchCriteria s=new SearchCriteria();
		s.setUserName("123");
		s.setValue("aaa");
		criteriaDao.saveOrUpdate(s);
		
		List<SearchCriteria> searchCriteriaList = criteriaDao.findAll();
		assertEquals(1,searchCriteriaList.size());
		
	}

}
