package com.blucargo.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.model.SearchCriteria;
import com.blucargo.services.SearchCriteriaService;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;

@RunWith(CargoIntegrationTestRunner.class)
public class SearchCriteriaServiceDbTest extends TransactionRollbackTestBase{

	@Inject
    SearchCriteriaService searchCriteriaService;
	
	@Before
	public void setUp(){
		
	}

	@Test
	public void testSaveSearchCriteria(){
		SearchCriteria s = new SearchCriteria();
		
		s.setValue("aaa");
		s.setUserName("kris");
		
		searchCriteriaService.save(s);
		assertTrue(searchCriteriaService.findAll().size() > 0);
		
	}
	

	@Test
	public void testSaveSearchCriteriaWithValue(){
		SearchCriteria s = new SearchCriteria();
		
		s.setValue("aaa");
		s.setUserName("kris");
		s.setValue("{\"departureCountry\":\"Algieria\",\"departureCity\":\"Alger\",\"arrivalCountry\":\"Anguilla\",\"arrivalCity\":\"Long Path\",\"body\":\"Platforma\",\"weight\":\"k2\"}");
		
		
		
		searchCriteriaService.save(s);
		assertTrue(searchCriteriaService.findAll().size() > 0);
		
	}

	
	@Test
	public void testFindByUser(){
		
		SearchCriteria c1 = new SearchCriteria();
		c1.setUserName("a");
		c1.setValue("aaa");

		SearchCriteria c2 = new SearchCriteria();
		c2.setUserName("b");
		c2.setValue("bbb");

		searchCriteriaService.save(c1);
		searchCriteriaService.save(c2);
		
		List<SearchCriteria> list = searchCriteriaService.findByUserName("b");
		SearchCriteria cBack = list.get(0);
		
		assertEquals("bbb", cBack.getValue());
	
	}
	
	@Test
	public void testFindByUserAndName(){
		SearchCriteria c1 = new SearchCriteria();
		c1.setUserName("a");
		c1.setValue("aaa");

		SearchCriteria c2 = new SearchCriteria();
		c2.setUserName("b");
		c2.setCriteriaName("b1");
		c2.setValue("bbb");

		SearchCriteria c3 = new SearchCriteria();
		c3.setUserName("b");
		c3.setCriteriaName("b2");
		c3.setValue("bbb");
		
		searchCriteriaService.save(c1);
		searchCriteriaService.save(c2);
		searchCriteriaService.save(c3);
		
		assertEquals(3, searchCriteriaService.findAll().size());

		List<SearchCriteria> list = searchCriteriaService.findAll();
		
		list = searchCriteriaService.findByUserNameAndCriteriaName("b","b2");
		SearchCriteria cBack = list.get(0);
		
		assertEquals("bbb", cBack.getValue());
		assertEquals("b2", cBack.getCriteriaName());
		
	}

	@Test
	public void testDeleteByUser(){
		SearchCriteria c1 = new SearchCriteria();
		c1.setUserName("a");
		c1.setValue("aaa");

		SearchCriteria c2 = new SearchCriteria();
		c2.setUserName("b");
		c2.setCriteriaName("b1");
		c2.setValue("bbb");

		SearchCriteria c3 = new SearchCriteria();
		c3.setUserName("b");
		c3.setCriteriaName("b2");
		c3.setValue("bbb");
		
		searchCriteriaService.save(c1);
		searchCriteriaService.save(c2);
		searchCriteriaService.save(c3);
		
		searchCriteriaService.deleteByUser("b");
		List<SearchCriteria> list = searchCriteriaService.findAll();

		assertEquals(1,list.size());
		assertEquals("aaa", list.get(0).getValue());
		
	}
	
	@Test
	public void testDeleteByUserAndName(){
		SearchCriteria c1 = new SearchCriteria();
		c1.setUserName("a");
		c1.setValue("aaa");

		SearchCriteria c2 = new SearchCriteria();
		c2.setUserName("b");
		c2.setCriteriaName("b1");
		c2.setValue("bbb");

		SearchCriteria c3 = new SearchCriteria();
		c3.setUserName("b");
		c3.setCriteriaName("b2");
		c3.setValue("bbb");
		
		searchCriteriaService.save(c1);
		searchCriteriaService.save(c2);
		searchCriteriaService.save(c3);
		
		searchCriteriaService.deleteByUserNameAndCriteriaName("b","b2");
		List<SearchCriteria> list = searchCriteriaService.findAll();

		assertEquals(2,list.size());
		assertEquals("aaa", list.get(0).getValue());
		assertEquals("bbb", list.get(1).getValue());
		
	}

	
	@Test
	public void testRemove(){
		
		SearchCriteria c1 = new SearchCriteria();
		c1.setUserName("a");
		c1.setValue("aaa");

		SearchCriteria c2 = new SearchCriteria();
		c2.setUserName("b");
		c2.setValue("bbb");

		searchCriteriaService.save(c1);
		searchCriteriaService.save(c2);
		
		List<SearchCriteria> list = searchCriteriaService.findAll();
		assertEquals(2, list.size());
		
		searchCriteriaService.removeById(c1.getId());
		searchCriteriaService.removeById(c2.getId());

		list = searchCriteriaService.findAll();
		assertEquals(0, list.size());
	
	}

	
	@Test
	public void testFindAllReturnsEmptyCollection(){
		List<SearchCriteria> list = searchCriteriaService.findAll();

		assertNotNull(list);
		assertTrue(list.size()==0);
	}

	@Test
	public void testFindByUserNameReturnsEmptyCollection(){
		List<SearchCriteria> list = searchCriteriaService.findByUserName("aaa");
		
		assertNotNull(list);
		assertTrue(list.size()==0);
	}
	
}
