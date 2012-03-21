package injector;

import com.blucargo.services.CargoOfferService;
import com.google.inject.Inject;

public class DummyInjectedClass {

	private CargoOfferService cargoOfferService;
	
	@Inject
	public DummyInjectedClass(CargoOfferService cargoOfferService){
		this.cargoOfferService = cargoOfferService;
	}


	public void setCargoOfferService(CargoOfferService cargoOfferService) {
		this.cargoOfferService = cargoOfferService;
	}


	public CargoOfferService getCargoOfferService() {
		return cargoOfferService;
	}
}
