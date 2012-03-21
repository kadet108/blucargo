package com.blucargo.services;

import java.util.List;

import com.blucargo.dao.AcceptedOfferDao;
import com.blucargo.dao.CargoOfferDao;
import com.blucargo.dao.OfferAcceptanceDao;
import com.blucargo.model.AcceptedOffer;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.OfferAcceptance;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class OfferAcceptanceService {

	private final OfferAcceptanceDao offerAcceptanceDao;
	private final CargoOfferDao cargoOfferDao;
	private final AcceptedOfferDao acceptedOfferDao;

	@Inject
	public OfferAcceptanceService(OfferAcceptanceDao offerAcceptanceDao, CargoOfferDao cargoOfferDao, AcceptedOfferDao acceptedOfferDao) {
		this.offerAcceptanceDao = offerAcceptanceDao;
		this.cargoOfferDao = cargoOfferDao;
		this.acceptedOfferDao = acceptedOfferDao;
	}

	public synchronized void save(List<OfferAcceptance> offerList) {
		for (OfferAcceptance oa : offerList) {
			offerAcceptanceDao.saveOrUpdate(oa);
		}
	}

	public synchronized void save(OfferAcceptance offer) {
		offerAcceptanceDao.saveOrUpdate(offer);
	}

	public synchronized void remove(List<OfferAcceptance> offerList) {
		for (OfferAcceptance oa : offerList) {
			this.removeOfferAcceptance(oa);
		}
	}

	public synchronized void removeOfferAcceptance(OfferAcceptance offerAcceptance) {
		offerAcceptanceDao.delete(offerAcceptance);

	}

	public synchronized List<OfferAcceptance> findAll() {
		return offerAcceptanceDao.findAll();
	}

	public synchronized OfferAcceptance findById(long id) {
		return offerAcceptanceDao.findById(id);
	}

	public void initiatorLogsOut(String string) {
		offerAcceptanceDao.deleteByInitiator(string);
	}

	public void initiateOffer(String initiator, String initiated, CargoOffer co) {

		OfferAcceptance offerAcceptance = offerAcceptanceDao.find(initiated, initiator, co.getId());

		// I dette tillfellet offerAcceptance er allrede i databasen.
		// Vi skal fjerne dette offerAcceptance og lage ny acceptOffer
		if (offerAcceptance != null) {
			
			AcceptedOffer acceptedOffer = new AcceptedOffer();
			CargoOffer cargoOffer = cargoOfferDao.findById(offerAcceptance.getOfferId());
			String owner = cargoOffer.getOwner();
			if (owner == initiated) {
				acceptedOffer.setUserName(initiator);
			} else {
				acceptedOffer.setUserName(initiated);
			}

			acceptedOffer.setOfferId(cargoOffer.getId());
			acceptedOfferDao.saveOrUpdate(acceptedOffer);
			offerAcceptanceDao.delete(offerAcceptance);

		} else {
			OfferAcceptance offer = new OfferAcceptance();
			offer.setInitiator(initiator);
			offer.setInitiated(initiated);
			offer.setOfferId(co.getId());
			offerAcceptanceDao.save(offer);
		}

	}

	// public void initiateOffer(OfferAcceptance oa) {
	// offerAcceptanceDao.save(oa);
	// }

}
