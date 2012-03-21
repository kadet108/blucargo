package com.blucargo.model.init;

import java.util.List;

import com.blucargo.model.AcceptedOffer;
import com.blucargo.model.CarBody;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.Country;
import com.blucargo.model.EndedOffer;
import com.blucargo.model.FavouriteOffer;
import com.blucargo.model.OfferAcceptance;
import com.blucargo.model.RegistrationData;
import com.blucargo.model.SearchCriteria;
import com.blucargo.persistence.TestPersistenceModule;
import com.blucargo.services.AcceptedOfferService;
import com.blucargo.services.CarBodyService;
import com.blucargo.services.CargoOfferService;
import com.blucargo.services.CountryService;
import com.blucargo.services.EndedOfferService;
import com.blucargo.services.FavouriteOfferService;
import com.blucargo.services.OfferAcceptanceService;
import com.blucargo.services.RegistrationDataService;
import com.blucargo.services.SearchCriteriaService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class CreateInitialData {

	
	public static void main(String[] args) {
		
		Injector injector = Guice.createInjector(new TestPersistenceModule());

		CargoOfferService cargoOfferService = injector.getInstance(CargoOfferService.class);
		AcceptedOfferService acceptedOfferService = injector.getInstance(AcceptedOfferService.class);
		FavouriteOfferService favouriteOfferService = injector.getInstance(FavouriteOfferService.class);
		EndedOfferService endedOfferService = injector.getInstance(EndedOfferService.class);
		CountryService countryService = injector.getInstance(CountryService.class);
		CarBodyService bodyService = injector.getInstance(CarBodyService.class);
		OfferAcceptanceService offerAcceptanceService = injector.getInstance(OfferAcceptanceService.class);
		SearchCriteriaService searchCriteriaService = injector.getInstance(SearchCriteriaService.class);
		RegistrationDataService registrationDataService = injector.getInstance(RegistrationDataService.class);
		
		List<Country> countries = new CreateCountriesData().Create();
		countryService.saveCountries(countries);

		List<CarBody> bodies = new CreateBodyData().Create();
		bodyService.saveBodies(bodies);

		List<CargoOffer> cOffers = new CreateCargoOffersData().Create();
		cargoOfferService.save(cOffers);

		List<FavouriteOffer> favouriteOffers = new CreateFavouriteOffersData(cOffers).Create();
		favouriteOfferService.saveFavouriteOffers(favouriteOffers);

		List<AcceptedOffer> acceptedOffers = new CreateAcceptedOffersData(cOffers).Create();
		acceptedOfferService.saveAcceptedOffers(acceptedOffers);

		List<EndedOffer> endedOffers = new CreateEndedOffersData(cOffers).Create();
		endedOfferService.saveEndedOffers(endedOffers);

		List<OfferAcceptance> acceptanceOffers = new CreateOfferAcceptanceData().Create();
		offerAcceptanceService.save(acceptanceOffers);

		List<SearchCriteria> searchCriteria = new SearchCriteriaData().Create();
		searchCriteriaService.save(searchCriteria);
		
		List<RegistrationData> regData = new CreateRegistrationData().Create();
		registrationDataService.save(regData);
		
		
	}
}