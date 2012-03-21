package org.jivesoftware.sparkimpl.plugin.blucargo.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.systray.evtHandler.ICargoOffersHandler;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.BlucargoSortableTableModel;

import com.blucargo.interfaces.ICargoOfferDao;
import com.blucargo.interfaces.ICommentDao;
import com.blucargo.interfaces.IEndedOfferDao;
import com.blucargo.interfaces.IRegistrationDataDao;
import com.blucargo.model.AcceptedOffer;
import com.blucargo.model.CarBody;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.City;
import com.blucargo.model.Comment;
import com.blucargo.model.CommentAndOffer;
import com.blucargo.model.Country;
import com.blucargo.model.EndedOffer;
import com.blucargo.model.FavouriteOffer;
import com.blucargo.model.OfferAcceptance;
import com.blucargo.model.RegistrationData;
import com.blucargo.model.SearchCriteria;

public class CargoDataManager
implements ICommentDao, ICargoOfferDao, IEndedOfferDao, IRegistrationDataDao
{

	private JTable cargoOffersTable;
	private JTable favouriteOffersTable;
	private JTable acceptedOffersTable;
	private JTable endedOffersTable;
	private List<CargoOffer> acceptedOffers;
	private List<CargoOffer> cargoOffers;
	private List<CarBody> carBodies;	
	private TreeMap<Long,CargoOffer> cargoOffersMap;
    private List<CargoOffer> favouriteOffers;
    private List<CargoOffer> endedOffers;
    private List<OfferAcceptance> offerAcceptanceList;

    private List<ICargoOffersHandler> cargoOfferListeners;

    private static final String blucargoPrefix = "http://myCargo.com/";
    
    private static CargoDataManager instance = new CargoDataManager();

    protected CargoDataManager() {
    	cargoOfferListeners = new ArrayList<ICargoOffersHandler>();
    }

    public static CargoDataManager getInstance() {
        return instance;
    }
    
    public static ICargoOfferDao getOfferDao() {
        return instance;
    }
    
    public static ICommentDao getCommentDao() {
        return instance;
    }
    public static IEndedOfferDao getEndedOfferDao() {
        return instance;
    }

    public static IRegistrationDataDao getRegistrationDataDao(){
    	return instance;
    }
    
    public void addOffersListener(ICargoOffersHandler listener)
    {
    	this.cargoOfferListeners.add(listener);
    }
    
    public void removeOffersListener(ICargoOffersHandler listener)
    {
    	for( ICargoOffersHandler offHandler : this.cargoOfferListeners )
    	{
    		if( cargoOfferListeners.equals(offHandler) )
    			cargoOfferListeners.remove(offHandler);
    	}
    }

    /*** ACCEPTED OFFERS **/
    public synchronized void addAcceptedOffer(AcceptedOffer co){
    	ArrayList<AcceptedOffer> acceptedOffers=new ArrayList<AcceptedOffer>();
    	acceptedOffers.add(co);
    	this.addAcceptedOffers(acceptedOffers);
    }

    public synchronized void removeAcceptedOffer(AcceptedOffer co){
    	ArrayList<AcceptedOffer> acceptedOffers=new ArrayList<AcceptedOffer>();
    	acceptedOffers.add(co);
    	this.removeAcceptedOffers(acceptedOffers);
    }
    
    public synchronized void addAcceptedOffers(ArrayList<AcceptedOffer> data) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "addAcceptedOffers");
        try {
        	iqCargoOffers.setValues(data);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

    public synchronized void removeAcceptedOffers(ArrayList<AcceptedOffer> data) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "removeAcceptedOffers");
        try {
        	iqCargoOffers.setValues(data);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    public synchronized List<CargoOffer> getAcceptedOffers() {
        if (acceptedOffers == null) {
            this.refreshAcceptedOffers();
        }
        return acceptedOffers;
    }

    public synchronized AcceptedOffer getAcceptedOfferByCargoOfferIdAndUserName(Long cargoOfferId) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getAcceptedOfferByCargoOfferAndUserName");
        
		try {
        	return (AcceptedOffer)iqCargoOffers.getValues(cargoOfferId);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        return null;
    }
    
    private synchronized void setAcceptedOffers(ArrayList<CargoOffer> data) {
    	this.acceptedOffers = data;
    }

    public JTable getAcceptedOffersTable() {
    	return acceptedOffersTable;
    }
    
    public void setAcceptedOffersTable(JTable acceptedOffersTable) {
    	this.acceptedOffersTable = acceptedOffersTable;
    }

    @SuppressWarnings("unchecked")
	public synchronized void refreshAcceptedOffers() {
    	if (SparkManager.getConnection() != null)
    	{
    		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getAcceptedOffers");
    		try {
    			CargoDataManager.getInstance().setAcceptedOffers((ArrayList<CargoOffer>)iqCargoOffers.getValues());
    		} catch (XMPPException ex) {
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    		} catch (IOException ex) {
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);

    			//To moze byc niebezpieczne
    			//this.refreshAcceptedOffers();
    		
    		} catch (Exception ex){
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    			//To moze byc niebezpieczne
    			//this.refreshAcceptedOffers();
    		}   		
    	}
    }
    
    /*** CARGO OFFERS***/
    public JTable getCargoOffersTable() {
    	return cargoOffersTable;
    }
    
    public void setCargoOffersTable(JTable cargoOffersTable) {
    	this.cargoOffersTable = cargoOffersTable;
    }
    
    @SuppressWarnings("unchecked")
	public synchronized void refreshCargoOffers() {
    	if (SparkManager.getConnection() != null)
    	{
    		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getCargoOffers");
    		try {
    			ArrayList<CargoOffer> cargoOffers=(ArrayList<CargoOffer>)iqCargoOffers.getValues();
    			CargoDataManager.getInstance().setCargoOffers(cargoOffers);
    			notifyCargoOffersRefreshed();
    		} catch (XMPPException ex) {
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    			

    		} catch (IOException ex) {
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    			//To moze byc niebezpieczne
    			//this.refreshCargoOffers();
    		} catch (Exception ex){
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    			//To moze byc niebezpieczne
    			//this.refreshCargoOffers();
    		}
    		
    	}
    }
    
    private void notifyCargoOffersRefreshed() {
		for(ICargoOffersHandler hnd : cargoOfferListeners)
		{
			hnd.onOffersRefresh();
		}
	}

	public static void refreshGUI(){
    	BlucargoSortableTableModel model = (BlucargoSortableTableModel) CargoDataManager.getInstance().getFavouriteOffersTable().getModel();
        model.refreshSlowly();
    }

    public synchronized void addCargoOffer(CargoOffer co){
    	ArrayList<CargoOffer> cargoOffers=new ArrayList<CargoOffer>();
    	cargoOffers.add(co);
    	this.addCargoOffers(cargoOffers);
    }

    public synchronized void removeCargoOffer(CargoOffer co){
    	ArrayList<CargoOffer> cargoOffers=new ArrayList<CargoOffer>();
    	cargoOffers.add(co);
    	this.removeCargoOffers(cargoOffers);
    }
    
    public synchronized void addCargoOffers(ArrayList<CargoOffer> data) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "addCargoOffers");
        try {
        	iqCargoOffers.setValues(data);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}

    }

    public synchronized void removeCargoOffers(ArrayList<CargoOffer> data) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "removeCargoOffers");
        try {
        	iqCargoOffers.setValues(data);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

    private synchronized void setCargoOffers(ArrayList<CargoOffer> data) {
        this.cargoOffers = data;
        this.cargoOffersMap = new TreeMap<Long,CargoOffer>();
        for (CargoOffer cargoOffer : data) {
			cargoOffersMap.put(cargoOffer.getId(), cargoOffer);
		}

    }

    public synchronized List<CargoOffer> getCargoOffers() {
        if (cargoOffers == null) {
            this.refreshCargoOffers();
        }
        return cargoOffers;
    }

    public synchronized CargoOffer getCargoOfferById(Long id) {
    	return this.cargoOffersMap.get(id);
    }
    
    public synchronized CargoOffer findOfferById(Long id) {
    	IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "findOfferById");
        try {
        	return (CargoOffer) iqCargoOffers.getValues(id);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public synchronized List<CargoOffer> getEndedCargoOffersByOwner(String owner) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getEndedCargoOffersByOwner");
        try {
        	return (List<CargoOffer>)iqCargoOffers.getValues(owner);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public List<EndedOffer> findEndedOfferByOwner(String owner) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "findEndedOfferByOwner");
        try {
        	return (List<EndedOffer>)iqCargoOffers.getValues(owner);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return null;
    }
    
    
    /*** COMMENT***/
    
    public synchronized void addComment(Comment comment) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "addComment");
        try {
        	iqCargoOffers.setValues(comment);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

    public synchronized void removeComment(Comment comment) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "removeComment");
        try {
        	iqCargoOffers.setValues(comment);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    @SuppressWarnings("unchecked")
	public synchronized List<Comment> findCommentsByOfferId(Long offerId) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "findCommentsByOfferId");
        try {
        	return (List<Comment>)iqCargoOffers.getValues(offerId);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public synchronized List<Comment> findCommentsByAuthor(String author) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "findCommentsByAuthor");
        try {
        	return (List<Comment>)iqCargoOffers.getValues(author);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public synchronized List<CommentAndOffer> findAllCommentsForUser(String user) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "findAllCommentsForUser");
        try {
        	return (List<CommentAndOffer>)iqCargoOffers.getValues(user);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return null;
    }

    @SuppressWarnings("unchecked")
	public synchronized List<Comment> findAllCommentsForUserSimple(String user) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "findAllCommentsForUserSimple");
        try {
        	return (List<Comment>)iqCargoOffers.getValues(user);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return null;
    }

    
    /*** FAVOURITE OFFERS ***/
    
    public synchronized List<CargoOffer> getFavouriteOffers() {
        if (favouriteOffers == null) {
            this.refreshFavouriteOffers();
        }
        return favouriteOffers;
    }

    private synchronized void setFavouriteOffers(ArrayList<CargoOffer> data) {
    	this.favouriteOffers = data;
    }

    public JTable getFavouriteOffersTable() {
    	return favouriteOffersTable;
    }
    
    public void setFavouriteOffersTable(JTable favouriteOffersTable) {
    	this.favouriteOffersTable = favouriteOffersTable;
    }
    
    @SuppressWarnings("unchecked")
	public synchronized void refreshFavouriteOffers() {
    	if (SparkManager.getConnection() != null)
    	{
    		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getFavouriteOffers");
    		try {
    			ArrayList<CargoOffer> favouriteOffers=(ArrayList<CargoOffer>)iqCargoOffers.getValues();
    			CargoDataManager.getInstance().setFavouriteOffers(favouriteOffers);
    		} catch (XMPPException ex) {
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    		} catch (IOException ex) {
    			
    			//To moze byc niebezpieczne. 
    			//this.refreshFavouriteOffers();
    			
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    		} catch (Exception ex){
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);

    			//To moze byc niebezpieczne. 
    			//this.refreshFavouriteOffers();

    		}   		
    	}
    }    

    public synchronized void addFavouriteOffer(FavouriteOffer co){
    	ArrayList<FavouriteOffer> favouriteOffers=new ArrayList<FavouriteOffer>();
    	favouriteOffers.add(co);
    	this.addFavouriteOffers(favouriteOffers);
    }

    public synchronized void addFavouriteOffers(ArrayList<FavouriteOffer> data) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "addFavouriteOffers");
        try {
        	iqCargoOffers.setValues(data);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

    public synchronized void removeFavouriteOffer(CargoOffer co){
    	ArrayList<CargoOffer> favouriteOffers=new ArrayList<CargoOffer>();
    	favouriteOffers.add(co);
    	this.removeFavouriteOffers(favouriteOffers);
    }

    public synchronized void removeFavouriteOffers(ArrayList<CargoOffer> data) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "removeFavouriteOffers");
        try {
        	iqCargoOffers.setValues(data);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
    /*** ENDED OFFERS ***/
    
    public synchronized List<CargoOffer> getEndedOffers() {
        if (endedOffers == null) {
            this.refreshEndedOffers();
        }
        return endedOffers;
    }

    private synchronized void setEndedOffers(ArrayList<CargoOffer> data) {
    	this.endedOffers = data;
    }

    public JTable getEndedOffersTable() {
    	return endedOffersTable;
    }
    
    public void setEndedOffersTable(JTable endedOffersTable) {
    	this.endedOffersTable = endedOffersTable;
    }
    
    @SuppressWarnings("unchecked")
	public synchronized void refreshEndedOffers() {
    	if (SparkManager.getConnection() != null)
    	{
    		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getEndedOffers");
    		try {
    			ArrayList<CargoOffer> endedOffers=(ArrayList<CargoOffer>)iqCargoOffers.getValues();
    			CargoDataManager.getInstance().setEndedOffers(endedOffers);
    		} catch (XMPPException ex) {
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    		} catch (IOException ex) {
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    			
    			//Moze byc niebezpieczne
    			//this.refreshEndedOffers();
    			
    		} catch (Exception ex){
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);

    			//Moze byc niebezpieczne
    			//this.refreshEndedOffers();
    		}   		
    	}
    }    

    public synchronized void addEndedOffer(EndedOffer co){
    	ArrayList<EndedOffer> endedOffers=new ArrayList<EndedOffer>();
    	endedOffers.add(co);
    	this.addEndedOffers(endedOffers);
    }

    public synchronized void addEndedOffers(ArrayList<EndedOffer> data) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "addEndedOffers");
        try {
        	iqCargoOffers.setValues(data);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

    public synchronized void removeEndedOffer(CargoOffer co){
    	ArrayList<CargoOffer> endedOffers=new ArrayList<CargoOffer>();
    	endedOffers.add(co);
    	this.removeEndedOffers(endedOffers);
    }

    public synchronized void removeEndedOffers(ArrayList<CargoOffer> data) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "removeEndedOffers");
        try {
        	iqCargoOffers.setValues(data);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

    /*** OFFER ACCEPTANCE ***/
    
    public synchronized List<OfferAcceptance> getOfferAcceptanceList() {
        if (offerAcceptanceList == null) {
            this.refreshOfferAcceptanceList();
        }
        return offerAcceptanceList;
    }

    private synchronized void setOfferAcceptanceList(ArrayList<OfferAcceptance> data) {
    	this.offerAcceptanceList = data;
    }
    
    @SuppressWarnings("unchecked")
	public synchronized void refreshOfferAcceptanceList() {
    	if (SparkManager.getConnection() != null)
    	{
    		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getOfferAcceptanceList");
    		try {
    			ArrayList<OfferAcceptance> offerAcceptanceList=(ArrayList<OfferAcceptance>)iqCargoOffers.getValues();
    			CargoDataManager.getInstance().setOfferAcceptanceList(offerAcceptanceList);
    		} catch (XMPPException ex) {
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    		} catch (IOException ex) {
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);    			
    			//Moze byc niebezpieczne
    			//this.refreshOfferAcceptanceList();
    			
    		} catch (Exception ex){
    			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
    			//Moze byc niebezpieczne
    			//this.refreshOfferAcceptanceList();
    		}   		
    	}
    }    

    public synchronized void initiateOfferAcceptance(String initiator,String initiated, CargoOffer co){
    	OfferAcceptance oa=new OfferAcceptance();
    	oa.setInitiator(initiator);
    	oa.setInitiated(initiated);
    	oa.setOfferId(co.getId());
    	
    	IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "initiateOfferAcceptance");
        try {
        	iqCargoOffers.setValues(oa);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

	public void initiatorLogsOut(String initiator) {
    	IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "initiatorLogsOutOfferAcceptance");
        try {
        	iqCargoOffers.setValues(initiator);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@SuppressWarnings("unchecked")
	public List<City> getCitiesByCountry(String c) {
    	IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getCitiesByCountry");
        try {
        	return (List<City>) iqCargoOffers.getValues(c);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        return null;
	}

	@SuppressWarnings("unchecked")
	public List<Country> getCountries() {
    	IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getCountries");
        try {
        	return (List<Country>) iqCargoOffers.getValues();
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CarBody> getBodies() {
		if (this.carBodies != null){
			return this.carBodies;
		}
		
    	IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getCarBodies");
        try {
        	this.carBodies = (List<CarBody>) iqCargoOffers.getValues();
        	return this.carBodies;
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        return null;
	}

	@SuppressWarnings("unchecked")
	public List<String> getEmployeesOf(String nickname) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getEmployeesOf");
        try {
        	return (List<String>)iqCargoOffers.getValues(nickname);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return null;
    }


	public String getUserProperty(String nickName, String key) {
		Object [] params = {nickName, key };
		
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getUserProperty");
        try {
        	return (String)iqCargoOffers.getValues(params);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        return null;
    }

	public String getUserVCardProperty(String nickName, String key) {
		Object [] params = {nickName, key };
		
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getUserVCardProperty");
        try {
        	return (String)iqCargoOffers.getValues(params);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        return null;
    }

	
	
	public void setUserProperty(String nickName, String key, String value) {

		Object [] params = {nickName, key, value};
		
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "setUserProperty");
        try {
        	iqCargoOffers.setValues(params);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

	public String saveOrUpdateUsers(List<Map<String,String>> employees) {
		
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "saveOrUpdateUsers");
        try {
        	return (String)iqCargoOffers.getValues(employees);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        return null;
    }

	@SuppressWarnings("unchecked")
	public Map<String,String> getUserInfo(String username) {
		
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getUserInfo");
        try {
        	return (Map<String,String>)iqCargoOffers.getValues(username);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        return null;
    }

	public void addSearchCriteria(SearchCriteria criteria) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "addSearchCritiera");
        try {
        	iqCargoOffers.setValues(criteria);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	@SuppressWarnings("unchecked")
	public List<SearchCriteria> getSearchCriterias() {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "findSearchCriteriasByUser");
        try {
        	return (List<SearchCriteria>)iqCargoOffers.getValues(null);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        return null;
	}

	/****COUNTRY****/
	
    @SuppressWarnings("unchecked")
	public synchronized List<Country> findAllCountry() {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "findAllCountry");
        try {
        	return (List<Country>)iqCargoOffers.getValues(null);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return null;
    }
	
	/****REGISTRATION****/
	
	public synchronized RegistrationData getUserByRegistrationNumber(String number) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "getUserByRegistrationNumber");
        try {
        	return (RegistrationData)iqCargoOffers.getValues(number);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return null;
    }
	
	public synchronized boolean checkLoginInTable(String login) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "checkLoginInTable");
        try {
        	return (Boolean)iqCargoOffers.getValues(login);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return false;
    }
	
	public synchronized boolean isUser(String user) {
		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), blucargoPrefix + "isUser");
        try {
        	return (Boolean)iqCargoOffers.getValues(user);
        } catch (XMPPException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
			Logger.getLogger(CargoDataManager.class.getName()).log(Level.SEVERE, null, ex);
		}
        return false;
    }
}
