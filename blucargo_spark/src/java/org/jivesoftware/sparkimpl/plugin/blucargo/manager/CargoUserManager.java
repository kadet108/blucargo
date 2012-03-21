package org.jivesoftware.sparkimpl.plugin.blucargo.manager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.spark.SparkManager;

public class CargoUserManager {

    private static CargoUserManager instance = null;

    protected CargoUserManager() {
    }

    public static CargoUserManager getInstance() {
        if (instance == null) {
            instance = new CargoUserManager();
        }
        return instance;
    }

    public synchronized String getUserJidFromNickname(String nickname) {
    	if (SparkManager.getConnection() != null)
    	{
    		IQCargoOffers iqCargoOffers = new IQCargoOffers(SparkManager.getConnection(), "http://myCargo.com/getUserJIDFromNickname");
    		try {
    			String userJid=(String) iqCargoOffers.getValues(nickname);
    			return userJid;
    		} catch (XMPPException ex) {
    			Logger.getLogger(CargoUserManager.class.getName()).log(Level.SEVERE, null, ex);
    		} catch (IOException ex) {
    			Logger.getLogger(CargoUserManager.class.getName()).log(Level.SEVERE, null, ex);
    		}    	
    	}
		return null;
    }
}
