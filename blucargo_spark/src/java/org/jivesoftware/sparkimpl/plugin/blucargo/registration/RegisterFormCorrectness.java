package org.jivesoftware.sparkimpl.plugin.blucargo.registration;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.jivesoftware.sparkimpl.plugin.blucargo.registration.enums.RegistrationFormEnum;

public class RegisterFormCorrectness {
    
    private HashMap<RegistrationFormEnum, Integer> data;
    private RegistrationFormEnum[] regFormEnum; 

    public RegisterFormCorrectness()
    {
    	data = new LinkedHashMap<RegistrationFormEnum, Integer>();
    	this.regFormEnum = RegistrationFormEnum.values();
    	reset();
    }
    
    public void reset()
    {
    	if(regFormEnum == null) return;
    	
    	for(RegistrationFormEnum form : regFormEnum)
    		data.put(form, 1);
    }

    public void setData(RegistrationFormEnum form, int value)
    {
    	if(!data.containsKey(form)) return;
    	
    	data.put(form, value);
    }

    public boolean isDataOk(RegistrationFormEnum form)
    {
    	if(!data.containsKey(form)) return false;
    	
    	return data.get(form) == 0;
    }
    
    public boolean isFormValid()
    {
    	for(RegistrationFormEnum form : regFormEnum)
    		if(data.get(form) != 0) return false;
    	
    	return true;
    }
    
    public RegistrationFormEnum getErrorStatus()
    {
    	if( data.isEmpty() ) return null;
    	
    	for(RegistrationFormEnum type : regFormEnum)
    		if(data.get(type) != 0) return type;
    	
    	return null;
    	
    }

	public int getData(RegistrationFormEnum type) {
		if( type == null ) return 0;
		else
		{
			return data.get(type);
		}
	}
}
    

 