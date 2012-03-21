package com.blucargo.persistence.testing;

import com.blucargo.persistence.PersistenceInitializer;
import com.google.inject.AbstractModule;
import com.wideplay.warp.persist.jpa.JpaUnit;

public class TestOpenJPAModule extends AbstractModule{
	
	@Override
	protected void configure() {
		bindConstant().annotatedWith(JpaUnit.class).to("test");
		bind(PersistenceInitializer.class).asEagerSingleton();
	}

}
