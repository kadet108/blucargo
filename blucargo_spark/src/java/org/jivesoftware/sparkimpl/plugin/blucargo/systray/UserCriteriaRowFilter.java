package org.jivesoftware.sparkimpl.plugin.blucargo.systray;

import javax.swing.RowFilter;

import org.apache.commons.lang.StringUtils;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoHotAmountManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoLocationManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoSearchManager;

import com.blucargo.model.CargoOffer;

public class UserCriteriaRowFilter extends RowFilter<Object, Object> {

	public boolean include(Entry entry) {
		String arrivalCountry 					= CargoSearchManager.getInstance().getArrivalCountry();
		String departureCountry 				= CargoSearchManager.getInstance().getDepartureCountry();
		String arrivalCity 						= CargoSearchManager.getInstance().getArrivalCity();
		String departureCity 					= CargoSearchManager.getInstance().getDepartureCity();
		String body 							= CargoSearchManager.getInstance().getBody();
		String weight 							= CargoSearchManager.getInstance().getWeight();
		
		boolean criteria = true;

		CargoOffer cargoOffer 					= (CargoOffer) entry.getValue(0);

		if (StringUtils.isNotEmpty(arrivalCountry)) {
			String translatedCountry 	= CargoLocationManager.getInstance().getCountries().get(arrivalCountry);
			criteria 					= StringUtils.equalsIgnoreCase(cargoOffer.getCountryTo(), translatedCountry);
		}
		if (StringUtils.isNotEmpty(departureCountry)) {
			String translatedCountry 	= CargoLocationManager.getInstance().getCountries().get(departureCountry);
			criteria 					= criteria && StringUtils.equalsIgnoreCase(cargoOffer.getCountryFrom(), translatedCountry);
		}
		if (StringUtils.isNotEmpty(arrivalCity)) {
			criteria = criteria && StringUtils.equalsIgnoreCase(cargoOffer.getCityTo(), arrivalCity);
		}
		if (StringUtils.isNotEmpty(departureCity)) {
			criteria = criteria && StringUtils.equalsIgnoreCase(cargoOffer.getCityFrom(), departureCity);
		}
		if (StringUtils.isNotEmpty(body)) {
			criteria = criteria && StringUtils.equalsIgnoreCase(cargoOffer.getBody(), body);
		}
		if (StringUtils.isNotEmpty(weight)) {
			criteria = criteria && StringUtils.equalsIgnoreCase(cargoOffer.getWeight(), weight);
		}
		
		criteria = criteria && ( cargoOffer.getId() >= CargoHotAmountManager.get().getLowestHotOfferId() );

		return criteria;
	}
	

}