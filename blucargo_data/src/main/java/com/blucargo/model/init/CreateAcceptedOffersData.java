package com.blucargo.model.init;

import java.util.ArrayList;
import java.util.List;

import com.blucargo.model.AcceptedOffer;
import com.blucargo.model.CargoOffer;


public class CreateAcceptedOffersData
{

	private List<CargoOffer> offers;
	
    public CreateAcceptedOffersData(List<CargoOffer> offers)
    {
        this.offers=offers;
    }

    public List<AcceptedOffer> Create()
    {
            List<AcceptedOffer> favouriteOffers=new ArrayList<AcceptedOffer>();

            AcceptedOffer fo=new AcceptedOffer();
            fo.setOfferId(offers.get(0).getId());
            fo.setUserName("a");

            AcceptedOffer f1=new AcceptedOffer();
            f1.setOfferId(offers.get(1).getId());
            f1.setUserName("a");

            AcceptedOffer f2=new AcceptedOffer();
            f2.setOfferId(offers.get(2).getId());
            f2.setUserName("a");

            AcceptedOffer f3=new AcceptedOffer();
            f3.setOfferId(offers.get(3).getId());
            f3.setUserName("a");

            favouriteOffers.add(fo);
            favouriteOffers.add(f1);
            favouriteOffers.add(f2);
            favouriteOffers.add(f3);

            return favouriteOffers;
    }

}
