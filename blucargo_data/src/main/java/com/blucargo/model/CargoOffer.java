package com.blucargo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="cargooffer")
public class CargoOffer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date loadingDate;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date unloadingDate;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date availableFrom;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date availableTo;
	

	private String price;
	private String comment;
	
	private String addressTo;
	private String addressFrom;
	private String body;
	private String cargoPrice;
	private String cashOnDelivery;
	private String cargoOnPallette;
	private String contact;
	private String countryFrom;
	private String countryTo;
	private String cityFrom;
	private String cityTo;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date deliveryOnDay;
	private String description;
	private String dimensionOfPallettes;
	private String elevator;
	private String hds;
	private long numberOfPallettes = 0;
	private String owner;
	private String otherDelivery;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date offerValid;
	private String postOfficeFrom;
	private String postOfficeTo;
	private String partLoad;
	private String readyToLoad;
	private String stackablePallettes;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date submissionDate;
	private OfferType type;

	private String unstackablePalettes;
	private String weight;
	private String cargoLength;
	private String cargoLoad;
	private String negotiable;
	private String volume;
	private boolean visible;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLoadingDate() {
		return loadingDate;
	}

	public void setLoadingDate(Date loadingDate) {
		this.loadingDate = loadingDate;
	}

	public Date getUnloadingDate() {
		return unloadingDate;
	}

	public void setUnloadingDate(Date unloadingDate) {
		this.unloadingDate = unloadingDate;
	}

	public OfferType getType() {
		return type;
	}

	public void setType(OfferType type) {
		this.type = type;
	}

	public Date getOfferValid() {
		return offerValid;
	}

	public void setOfferValid(Date offerValid) {
		this.offerValid = offerValid;
	}

	public String getCountryFrom() {
		return countryFrom;
	}

	public void setCountryFrom(String countryFrom) {
		this.countryFrom = countryFrom;
	}

	public String getCountryTo() {
		return countryTo;
	}

	public void setCountryTo(String countryTo) {
		this.countryTo = countryTo;
	}

	public String getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}

	public String getCityTo() {
		return cityTo;
	}

	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}

	public String getPostOfficeFrom() {
		return postOfficeFrom;
	}

	public void setPostOfficeFrom(String poFrom) {
		this.postOfficeFrom = poFrom;
	}

	public String getPostOfficeTo() {
		return postOfficeTo;
	}

	public void setPostOfficeTo(String poTo) {
		this.postOfficeTo = poTo;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getHds() {
		return hds;
	}

	public void setHds(String hds) {
		this.hds = hds;
	}

	public String getElevator() {
		return elevator;
	}

	public void setElevator(String elevator) {
		this.elevator = elevator;
	}

	public String getAddressTo() {
		return addressTo;
	}

	public void setAddressTo(String adr) {
		this.addressTo = adr;
	}

	public String getAddressFrom() {
		return addressFrom;
	}
	
	public void setAddressFrom(String adr) {
		this.addressFrom = adr;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPartLoad() {
		return partLoad;
	}

	public void setPartLoad(String part_load) {
		this.partLoad = part_load;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getReadyToLoad() {
		return readyToLoad;
	}

	public void setReadyToLoad(String ready_to_load) {
		this.readyToLoad = ready_to_load;
	}

	public String getCargoOnPalette() {
		return cargoOnPallette;
	}

	public void setCargoOnPalette(String cargo_on_palette) {
		this.cargoOnPallette = cargo_on_palette;
	}

	public String getNegotiable() {
		return negotiable;
	}

	public void setNegotiable(String negotiable) {
		this.negotiable = negotiable;
	}

	public String getCashOnDelivery() {
		return cashOnDelivery;
	}

	public void setCashOnDelivery(String cash_on_delivery) {
		this.cashOnDelivery = cash_on_delivery;
	}

	public Date getDeliveryOnDay() {
		return deliveryOnDay;
	}

	public void setDeliveryOnDay(Date delivery_on_day) {
		this.deliveryOnDay = delivery_on_day;
	}

	public long getNumberOfPalettes() {
		return numberOfPallettes;
	}

	public void setNumberOfPalettes(long number_of_palettes) {
		this.numberOfPallettes = number_of_palettes;
	}

	public String getDimensionOfPalettes() {
		return dimensionOfPallettes;
	}

	public void setDimensionOfPalettes(String dimension_of_palettes) {
		this.dimensionOfPallettes = dimension_of_palettes;
	}

	public String getStackablePalettes() {
		return stackablePallettes;
	}

	public void setStackablePalettes(String stackable_palettes) {
		this.stackablePallettes = stackable_palettes;
	}

	public String getUnstackablePalettes() {
		return unstackablePalettes;
	}

	public void setUnstackablePalettes(String unstackable_palettes) {
		this.unstackablePalettes = unstackable_palettes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCargoPrice(String cargoPrice) {
		this.cargoPrice = cargoPrice;
	}

	public String getCargoPrice() {
		return cargoPrice;
	}

	public String getOther_delivery() {
		return otherDelivery;
	}

	public void setOtherDelivery(String other_delivery) {
		this.otherDelivery = other_delivery;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}
	
	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Date getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(Date availableTo) {
		this.availableTo = availableTo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCargoLength() {
		return cargoLength;
	}

	public void setCargoLength(String cargoLength) {
		this.cargoLength = cargoLength;
	}

	public String getCargoLoad() {
		return cargoLoad;
	}

	public void setCargoLoad(String cargoLoad) {
		this.cargoLoad = cargoLoad;
	}

	
}
