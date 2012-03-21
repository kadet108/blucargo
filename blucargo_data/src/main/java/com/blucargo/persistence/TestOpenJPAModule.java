package com.blucargo.persistence;

import com.google.inject.AbstractModule;
import com.wideplay.warp.persist.jpa.JpaUnit;

public class TestOpenJPAModule extends AbstractModule{

	@Override
	protected void configure() {
		bindConstant().annotatedWith(JpaUnit.class).to("create_schema");
		bind(PersistenceInitializer.class).asEagerSingleton();
	}

}
