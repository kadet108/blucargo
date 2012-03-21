package com.blucargo.persistence;

import com.google.inject.AbstractModule;
import com.wideplay.warp.persist.jpa.JpaUnit;

public class OpenJPAModule extends AbstractModule{

	@Override
	protected void configure() {
		bindConstant().annotatedWith(JpaUnit.class).to("main");
		bind(PersistenceInitializer.class).asEagerSingleton();
	}

}
