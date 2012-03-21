package com.blucargo.utils.validation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class InputFieldVerifier {
	
	private static final InputFieldVerifier instance = new InputFieldVerifier();
	
	public static InputFieldVerifier get()
	{
		return instance;
	}
	
	List<String> asciiForNames = new ArrayList<String>();
	String asciiForNamesString ="";
	
	String lacalCase = "";
	String usernameReg = "([a-zA-Z1-9]{1,20})$";
	String password;
	String emailReg = "([a-zA-Z0-9_\\.-]+)@([\\da-zA-Z\\.-]+)\\.([a-zA-Z\\.]{2,6}$)";
	String cell1Reg = "(\\+[1-9]{2}[0-9]{9})$";
	String cell2Reg = "(\\+[1-9]{2}-[0-9]{3}-[0-9]{3}-[0-9]{3}$)";
	String cell3Reg = "([0-9]{3}-[0-9]{3}-[0-9]{3}$)|[0-9]{9}$";
	String cellReg = cell1Reg+"|"+cell2Reg+"|"+cell3Reg;
	String polishPostCodeReg = "[0-9]{2}-[0-9]{3}$";
	String postCodeReg = "[a-zA-Z0-9\\.-]{1,9}$";
	String nipReg = "([0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}$)|([0-9]{3}-[0-9]{2}-[0-9]{2}-[0-9]{3}$)";
	String companyNameReg = "(.){1,50}$";
	String adressReg = "(.){1,50}$";
	String cityReg;
	String nameReg;
	String surnameReg;
	String upperCaseReg;
	String namesReg;
	
	int PASSWORD_LENGHT = 3;
	int UPPERCASE_SIGN_COUNT = 1;
	int NUMBER_SIGN_COUNT = 1;
	
	Color goodColor = Color.black;
	
	private InputFieldVerifier(){
		fillAsciiListForNames();
		upperCaseReg = "[A-Z"+lacalCase.toUpperCase()+"]";
		namesReg = "[A-Z"+lacalCase.toUpperCase()+"]{1}[a-z"+lacalCase.toLowerCase()+"]{0,15}";
		nameReg = namesReg+"$";
		surnameReg = namesReg+"|"+namesReg+"-"+namesReg+"$";
		cityReg =  namesReg+"|"+namesReg+"\\s"+namesReg+"|"+namesReg+"-"+namesReg+"$";
	}
	
	public int verifyUsername(String username){
		return checkStringNotEmtptyAndMatch(username, usernameReg);
	}
	
	public int verifyEmail(String email){
		return checkStringNotEmtptyAndMatch(email, emailReg);
	}
	
	public int verifyCell(String cell){
		return checkStringNotEmtptyAndMatch(cell, cellReg);
	}

	public int verifyNip(String nip){
		return checkStringNotEmtptyAndMatch(nip, nipReg);
	}

	public int verifyPostCode(String post, String country){
		if(country == "Polska"){
			if(!isPostCodeValid(post, polishPostCodeReg))
				return 1;
			else
				return 0;
		}
		else
			if(!isPostCodeValid(post, postCodeReg))
				return 2;
			else 
				return 0;
	}

	public int verifyCountry(String post, String country){
		if(country == "-----------")
			return 1;
		else
			return 0;
	}
	
	public int verifyNameAndSurname(String str, String regexp){
		if(str != null && str.length() != 0)
		{
			if( !isCharUpperCase( str.toCharArray()[0] ) )
				return 2;
			else
			{
				return str.matches(regexp) ? 0 : 1;
			}
		}
		return 1;
	}
	
	public int verifyName(String name){
		return verifyNameAndSurname(name, nameReg);
	}
	
	public int verifySurname(String surname){
		return verifyNameAndSurname(surname, surnameReg);
	}
	
	public int verifyCompanyName(String name){
		return checkStringNotEmtptyAndMatch(name, companyNameReg);
	}

	private int checkStringNotEmtptyAndMatch(String data, String regexp) {
		if(data.length() > 0){
			return data.matches(regexp) ? 0 : 1;
		}
		else
			return 1;
	}
	
	public int verifyAdress(String adress){
		return checkStringNotEmtptyAndMatch(adress, adressReg);
	}
	
	public int verifyCity(String city){
		return checkStringNotEmtptyAndMatch(city, cityReg);
	}
	
	public int isStatuteAccepted(boolean bool){
		return bool ? 0 : 1;
	}
	
	public boolean isPostCodeValid(String post, String postReg){
		return post.matches(postReg);
	}
	
	private void fillAsciiListForNames(){
		String s;
		for(int i = 192; i<383; i++){
			if(i==215)
				i=217;
			if(i==247)
				i=249;
			s = String.valueOf((char)i);
			asciiForNames.add(s);
			asciiForNamesString+=s;
			lacalCase+=s;
			}
	}
	
	public boolean hasUpperCase(int upperCaseSignNum, String password){
		return matchesRegExp(upperCaseSignNum, "[A-Z]", password);
	}
	
	public boolean hasNumbers(int numberSignNum, String password){
		return matchesRegExp(numberSignNum, "[0-9]", password);
	}
	
	private final String anySign = ".*"; 
	
	public boolean matchesRegExp(int regexpNum, String regexp, String password){
		
		if( regexp == null || regexp.length() == 0 ) return false;
		
		StringBuffer buf = new StringBuffer(regexpNum * regexp.length() + 3);
		for(int i=0; i<regexpNum; i++)
		{
			buf.append(anySign);
			buf.append(regexp);
		}
		buf.append(anySign);
		return password.matches(buf.toString());
	}
	
	
	
	public int isPasswordValid(String password, String confirmPassword){
		
		if( password == null || password.length() < PASSWORD_LENGHT ) return 1;		
		if( !hasUpperCase(UPPERCASE_SIGN_COUNT, password) ) return 2;
		if( !hasNumbers(NUMBER_SIGN_COUNT, password) ) return 3;
		if( confirmPassword == null || confirmPassword.length() != password.length() || !confirmPassword.equals(password) ) return 4;

		
		return 0;
	}
	
	public boolean isCharUpperCase(char c){
		return String.valueOf(c).matches(upperCaseReg);
	}
}
