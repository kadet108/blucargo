package org.jivesoftware.sparkimpl.plugin.blucargo.manager;


public class CargoSearchManager {

	private static CargoSearchManager instance = null;

	protected CargoSearchManager() {
	}

	public static CargoSearchManager getInstance() {
		if (instance == null) {
			instance = new CargoSearchManager();
		}
		return instance;
	}

	private String arrivalCountry;
	private String departureCountry;
	private String arrivalCity;
	private String departureCity;
	private String body;
	private String weight;

	public String getArrivalCountry() {
		return arrivalCountry;
	}

	public void setArrivalCountry(String arrivalCountry) {
		this.arrivalCountry = arrivalCountry;
	}

	public String getDepartureCountry() {
		return departureCountry;
	}

	public void setDepartureCountry(String departureCountry) {
		this.departureCountry = departureCountry;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

}