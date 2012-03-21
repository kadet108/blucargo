package com.blucargo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.blucargo.model.AcceptedOffer;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.SearchCriteria;
import com.blucargo.testing.CargoIntegrationTestRunner;
import com.blucargo.testing.TransactionRollbackTestBase;
import com.google.inject.Inject;


public class SearchCriteriaModelTest {

	@Test
	public void testValueField() throws Exception {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setValue("123qwe");
		assertEquals("123qwe", searchCriteria.getValue());
	}

	@Test
	public void testUserNameField() throws Exception {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setUserName("123qwe");
		assertEquals("123qwe", searchCriteria.getUserName());
	}

	
}
