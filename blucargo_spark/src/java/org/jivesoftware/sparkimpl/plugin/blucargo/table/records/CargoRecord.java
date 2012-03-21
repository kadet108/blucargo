package org.jivesoftware.sparkimpl.plugin.blucargo.table.records;

import com.blucargo.model.CargoOffer;


public class CargoRecord implements Record {

    static String[] headers = {"Info",
        "Za\u0142adunek",
        "Roz\u0142adunek",
        "Pojazd",
        "Zg\u0142oszono",
        "Wa\u017cne do",
        "Kontakt"
    };
    
    static int counter;
    private CargoOffer cargoOffer;
    String[] data;

    public CargoRecord(CargoOffer c) {
        cargoOffer = c;
    }

    public String getValueAt(int i) {
        Object returnVal = null;

        switch (i) {
            case 0:
                returnVal = getOffer().getLoadingDate();
                break;
            case 1:
                returnVal = getOffer().getCountryFrom();
                break;
            case 2:
                returnVal = getOffer().getCountryTo();
                break;
            case 3:
                returnVal = getOffer().getBody();
                break;
            case 4:
                returnVal = getOffer().getSubmissionDate();
                break;
            case 5:
                returnVal = getOffer().getOfferValid();
                break;
            case 6:
                returnVal = getOffer().getContact();
                break;
            default:
                break;

        };

        if (returnVal != null) {
            return returnVal.toString();
        } else {
            return "";
        }
    }

    public String getColumnName(int i) {
        return headers[i];
    }

    public int getColumnCount() {
        return headers.length;
    }

    public CargoOffer getOffer() {
        return cargoOffer;
    }

    public void setOffer(CargoOffer cOffer) {
        this.cargoOffer = cOffer;
    }
}
