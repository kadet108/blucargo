package com.blucargo.testing;

import org.junit.runners.model.InitializationError;

import com.blucargo.persistence.testing.TestPersistenceModule;


public class CargoIntegrationTestRunner extends GuiceTestRunner {

//    static {
//        // Use test environment configuration
//        System.setProperty("CONSTRETTO_TAGS", "test");
//    }

    public CargoIntegrationTestRunner(Class<?> classToRun) throws InitializationError {
        super(classToRun, new TestPersistenceModule());
    }

}
