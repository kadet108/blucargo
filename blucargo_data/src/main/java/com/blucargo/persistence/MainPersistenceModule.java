package com.blucargo.persistence;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.Transactional;
import com.wideplay.warp.persist.UnitOfWork;

public class MainPersistenceModule extends AbstractModule{

	@Override
	protected void configure() {
		install(new OpenJPAModule());
		install(PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION).forAll(Matchers.annotatedWith(Transactional.class),Matchers.any()).buildModule());
	}

}
