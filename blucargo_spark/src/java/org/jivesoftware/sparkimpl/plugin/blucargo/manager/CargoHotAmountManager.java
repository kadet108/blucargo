package org.jivesoftware.sparkimpl.plugin.blucargo.manager;

import java.util.ArrayList;
import java.util.List;

import com.blucargo.model.CargoOffer;

public class CargoHotAmountManager {
	
	public static final int BUFFER_SIZE = 3;
	
	private static CargoHotAmountManager instance = new CargoHotAmountManager();
	
	public static CargoHotAmountManager get()
	{
		return instance;
	}
	
	long lowestHotOfferId = 0;
	private List<Long> idList = new ArrayList<Long>();
	
	public void appendTopOfferId( List<CargoOffer> coList )
	{
		long lo;
		
		for(CargoOffer co : coList)
		{
			lo = co.getId();
			if( ( lo ) > lowestHotOfferId && !idList.contains(lo) )
			{
				idList.add( lo );
						
				if( idList.size() > BUFFER_SIZE )
				{
					idList.remove( lowestHotOfferId );
				}
				
				setLowestHotOfferId( voteForLowestId() );
			}
		}

	}
	
	private long voteForLowestId() {
		
		long min = 0;
		boolean b = true;
		
		for( long lo : idList )
		{
			if( b )
			{
				min = lo;
				b = false;
			}
			else
			{
				if( lo < min )
				{
					min = lo;
				}
			}
		}
		
		return min;	
	}

	public long getLowestHotOfferId() {
		return lowestHotOfferId;
	}

	private void setLowestHotOfferId(long mIN) {
		lowestHotOfferId = mIN;
	}
	
	public int getBufferSize()
	{
		return BUFFER_SIZE;
	}
}
