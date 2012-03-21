package com.blucargo.persistence;

import com.google.inject.Inject;
import com.wideplay.warp.persist.PersistenceService;

public class PersistenceInitializer {

	@Inject
	public PersistenceInitializer(PersistenceService service){
		service.start();
	}
	
}
