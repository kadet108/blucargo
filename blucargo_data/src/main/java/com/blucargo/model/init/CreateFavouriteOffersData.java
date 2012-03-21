package com.blucargo.model.init;

import java.util.ArrayList;
import java.util.List;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.FavouriteOffer;


public class CreateFavouriteOffersData
{

	private List<CargoOffer> offers;
	
    public CreateFavouriteOffersData(List<CargoOffer> offers)
    {
        this.offers=offers;
    }

    public List<FavouriteOffer> Create()
    {
            ArrayList<FavouriteOffer> favouriteOffers=new ArrayList<FavouriteOffer>();

            FavouriteOffer fo=new FavouriteOffer();
            fo.setOfferId(offers.get(0).getId());
            fo.setUserName("a");

            FavouriteOffer f1=new FavouriteOffer();
            f1.setOfferId(offers.get(1).getId());
            f1.setUserName("a");

            favouriteOffers.add(fo);
            favouriteOffers.add(f1);

            return favouriteOffers;
    }

}
