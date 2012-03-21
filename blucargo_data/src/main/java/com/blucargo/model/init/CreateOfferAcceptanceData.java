package com.blucargo.model.init;

import java.util.ArrayList;
import java.util.List;

import com.blucargo.model.OfferAcceptance;

public class CreateOfferAcceptanceData
{

    public List<OfferAcceptance> Create()
    {
            ArrayList<OfferAcceptance> offers=new ArrayList<OfferAcceptance>();

            OfferAcceptance o=new OfferAcceptance();
            o.setInitiator("a");
            o.setInitiated("b");
            o.setOfferId(1L);
            
            OfferAcceptance o1=new OfferAcceptance();
            o1.setInitiator("a");
            o1.setInitiated("b");
            o1.setOfferId(2L);

            OfferAcceptance o2=new OfferAcceptance();
            o2.setInitiator("b");
            o2.setInitiated("a");
            o2.setOfferId(3L);

            offers.add(o);
            offers.add(o1);
            offers.add(o2);

            return offers;
    }

}
