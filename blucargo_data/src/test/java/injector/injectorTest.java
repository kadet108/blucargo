package injector;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.blucargo.persistence.MainPersistenceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class injectorTest {

	Injector injector;
	
	@Before
	public void setUp(){
		injector=Guice.createInjector(new MainPersistenceModule());
	}

	
	@Test
	public void injectorTest(){
		DummyInjectedClass clazz = injector.getInstance(DummyInjectedClass.class);
		assertNotNull(clazz.getCargoOfferService());
	}

}
