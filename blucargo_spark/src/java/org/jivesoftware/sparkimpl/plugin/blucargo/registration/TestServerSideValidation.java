package org.jivesoftware.sparkimpl.plugin.blucargo.registration;

import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smackx.packet.VCard;

public class TestServerSideValidation {
	
	private static final TestServerSideValidation instance = new TestServerSideValidation();
	
	public static TestServerSideValidation get(){
		return instance;
	}
	
    public String generateVCardXmlString(){
    	VCard vCard = new VCard();
    	
    	
		vCard.setNickName("");
		vCard.setFirstName("imieZle");
		vCard.setLastName("nazwiskoZle");
		vCard.setOrganization("");
		vCard.setAddressFieldWork("LOCALITY", "");
		vCard.setAddressFieldWork("REGION", "");
		vCard.setAddressFieldWork("CTRY", "Polska");
		vCard.setPhoneWork("CELL", "telefonZly");
		vCard.setEmailWork("emailZly");
		vCard.setField("NIP", "11111111Nipzly");
		vCard.setField("POST", "nieDlaPolski");
		
		return vCard.toString();
    }
    
    private Map<String, String> createUrlPostParamMap() {
    	
    	Map<String, String> params = new HashMap<String, String>(4);
    	params.put("login", "");
    	params.put("password", "zleHaslo");
    	params.put("email", "zlyEmail");
    	params.put("vCard", generateVCardXmlString());
		return params;
	}
    
    public String sendTestRegData(){
    	String endpoint = "http://localhost:9090/cargo/registration.do";
    	return HttpRequestUtils.sendPostRequest(endpoint, createUrlPostParamMap());
    }
}
