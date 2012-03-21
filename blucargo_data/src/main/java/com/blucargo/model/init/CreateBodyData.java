package com.blucargo.model.init;

//import org.jivesoftware.sparkimpl.plugin.cargo.model.*;
import java.util.ArrayList;

import com.blucargo.model.CarBody;

public class CreateBodyData {

    public CreateBodyData()
    {
        
    }

    public ArrayList<CarBody> Create()
    {
            ArrayList<CarBody> bodies=new ArrayList<CarBody>();
            bodies.add(new CarBody("Plandeka"));
            bodies.add(new CarBody("Izoterma"));
            bodies.add(new CarBody("Platforma"));
            bodies.add(new CarBody("Wywrotka"));
            bodies.add(new CarBody("Jumbo"));
            bodies.add(new CarBody("Chłodnia"));
            bodies.add(new CarBody("Firanka"));
            bodies.add(new CarBody("Cysterna"));
            bodies.add(new CarBody("Silos"));
            bodies.add(new CarBody("Meblowóz"));
            bodies.add(new CarBody("Kontener"));
            bodies.add(new CarBody("Zestaw"));
            bodies.add(new CarBody("Bus"));
            bodies.add(new CarBody("Laweta"));
            bodies.add(new CarBody("Mega"));
            bodies.add(new CarBody("Coilmulde"));
            bodies.add(new CarBody("Inne"));

            return bodies;
    }

}
