package com.blucargo.model.init;

import java.util.ArrayList;

import com.blucargo.model.RegistrationData;

public class CreateRegistrationData {
	
    public CreateRegistrationData()
    {

    }
    
	public ArrayList<RegistrationData> Create(){
		ArrayList<RegistrationData> registrationData = new ArrayList<RegistrationData>();
		
		RegistrationData regData1 = new RegistrationData();
		regData1.setPassword("Haslo1");
		regData1.setRegId("1234567890123456789012345678901234567890123456789012345678901234");
		regData1.setUserName("Radek");
		regData1.setvCard("aaaaaaaaaabbbbbbbbbbccccccccccccddddddddddeeeeeeeeeee" +
						 "fffffffffffggggggggggghhhhhhhhhhhhhiiiiiiiiiiijjjjjjjjj");
		
		RegistrationData regData2 = new RegistrationData();
		regData2.setPassword("Haslo2");
		regData2.setRegId("1234567890123456789012345678901234567890123456789012345678901235");
		regData2.setUserName("Lukasz");
		regData2.setvCard("aaaaaaaaaabbbbbbbbbbccccccccccccddddddddddeeeeeeeeeee" +
						 "fffffffffffggggggggggghhhhhhhhhhhhhiiiiiiiiiiijjjjjjjjj");
		
		RegistrationData regData3 = new RegistrationData();
		regData3.setPassword("Haslo3");
		regData3.setRegId("1234567890123456789012345678901234567890123456789012345678901236");
		regData3.setUserName("Krzysiek");
		regData3.setvCard("aaaaaaaaaabbbbbbbbbbccccccccccccddddddddddeeeeeeeeeee" +
						 "fffffffffffggggggggggghhhhhhhhhhhhhiiiiiiiiiiijjjjjjjjj");
		
		registrationData.add(regData1);
		registrationData.add(regData2);
		registrationData.add(regData3);
		
		return registrationData;

	}

}
