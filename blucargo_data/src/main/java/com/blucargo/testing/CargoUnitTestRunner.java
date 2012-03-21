package com.blucargo.testing;

import java.lang.reflect.InvocationTargetException;

import org.mockito.runners.MockitoJUnitRunner;


public class CargoUnitTestRunner extends MockitoJUnitRunner {

//    static {
//        // Use test environment configuration
//        System.setProperty("CONSTRETTO_TAGS", "test");
//    }

    public CargoUnitTestRunner(Class<?> klass) throws InvocationTargetException {
    	super(klass);
    }

}
