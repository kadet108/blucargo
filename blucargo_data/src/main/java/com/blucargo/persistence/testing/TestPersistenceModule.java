package com.blucargo.persistence.testing;

import com.google.inject.AbstractModule;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

public class TestPersistenceModule extends AbstractModule{

	@Override
	protected void configure() {
		install(new TestOpenJPAModule());
		install(PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION).buildModule());
	}

}
