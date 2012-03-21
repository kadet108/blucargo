package com.blucargo.model.init;

import java.util.ArrayList;
import java.util.List;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.EndedOffer;


public class CreateEndedOffersData
{

	private List<CargoOffer> offers;
	
    public CreateEndedOffersData(List<CargoOffer> offers)
    {
        this.offers=offers;
    }

    public List<EndedOffer> Create()
    {
            List<EndedOffer> endedOffers=new ArrayList<EndedOffer>();

            EndedOffer fo=new EndedOffer();
            fo.setOfferId(offers.get(0).getId());
            fo.setUserName("a");

            EndedOffer f1=new EndedOffer();
            f1.setOfferId(offers.get(1).getId());
            f1.setUserName("a");

            EndedOffer f2=new EndedOffer();
            f2.setOfferId(offers.get(2).getId());
            f2.setUserName("a");

            EndedOffer f3=new EndedOffer();
            f3.setOfferId(offers.get(3).getId());
            f3.setUserName("a");

            endedOffers.add(fo);
            endedOffers.add(f1);
            endedOffers.add(f2);
            endedOffers.add(f3);

            return endedOffers;
    }

}
