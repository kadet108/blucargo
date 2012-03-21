package org.jivesoftware.sparkimpl.plugin.blucargo.registration.enums;

import org.jivesoftware.sparkimpl.plugin.blucargo.registration.InputFieldVerifier;
import org.jivesoftware.sparkimpl.plugin.blucargo.registration.RegistrationStringConstants;

public enum RegistrationFormEnum {
	
	USERNAME(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return InputFieldVerifier.get().verifyUsername((String) data);
			}
		},
		new String[]{ "Wype\u0142nij pol\u0119 u\u017cytkownik." }
	),
	PASSWORD(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				DoubleString db = (DoubleString) data;
				return InputFieldVerifier.get().isPasswordValid(db.getString1(), db.getString2());
			}
			
		}, 
		RegistrationStringConstants.passwordErrorMsg
	),
	SERVER(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return 0;
			}
		},
		new String[]{ "D" }
	),
	NAME(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return InputFieldVerifier.get().verifyName((String) data);
			}
			
		},
		new String[]{ "Wpisz prawid\u0142owo imi\u0119.",
					  "Imi\u0119 musi zaczyna\u0107 si\u0119 z du\u017cej litery."}
	),
	SURNAME(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return InputFieldVerifier.get().verifySurname((String) data);
			}
			
		},
		new String[]{ "Wpisz prawid\u0142owo Nazwisko.",
				  	  "Nazwisko musi zaczyna\u0107 si\u0119 z du\u017cej litery." }
	),
	COMPANY_NAME(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return InputFieldVerifier.get().verifyCompanyName((String) data);
			}
			
		},
		new String[]{ "Niepoprawnie wype\u0142nione pole nazwa firmy." }
	),
	NIP(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return InputFieldVerifier.get().verifyNip((String) data);
			}
			
		},
		new String[]{ "Nip powinien mie\u0107 posta\u0107 111-111-11-11 lub 111-11-11-111." }
		
	),
	ADRESS(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return InputFieldVerifier.get().verifyAdress((String) data);
			}
			
		},
		new String[]{ "Niepoprawnie wype\u0142nione pole nazwa adres." }
	),
	POST_CODE(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				DoubleString db = (DoubleString) data;
				return InputFieldVerifier.get().verifyPostCode(db.getString1(), db.getString2());
			}		
		},
		new String[]{ "Dla polski kod pocztowy ma wz\u00f3r: 99-999.",
						"Wpisz kod pocztowy."}
	),
	CITY(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return InputFieldVerifier.get().verifyCity((String) data);
			}
			
		},
		new String[]{ "Niepoprawnie wype\u0142nione pole nazwa miasto.",
						"Miasto musi si\u0119 zaczyna\u0107 z du\u017cej litery."}
	),
	COUNTRY(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				DoubleString db = (DoubleString) data;
				return InputFieldVerifier.get().verifyCountry(db.getString1(), db.getString2());
			}
			
		},
		new String[]{ "Musisz poda\u0107 kraj." }
	),
	CELL(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return InputFieldVerifier.get().verifyCell((String) data);
			}
			
		},
		new String[]{ "Telefon musi mie\u0107 posta\u0107: (+48) 123 123 123 lub 123 123 123." }
		
	),
	EMAIL(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return InputFieldVerifier.get().verifyEmail((String) data);
			}
			
		},
		new String[]{ "Niepoprawnie wype\u0142nione pole email." }		
	),
	ACCEPTANCE(new IFieldVerifier(){
			@Override
			public <Data> int verifyData(Data data) {
				return InputFieldVerifier.get().isStatuteAccepted((Boolean) data);
			}	
		},
		new String[]{ "Potwierdz regulamin." }
	);

	IFieldVerifier fieldVerifier;
	String[] errorMessage;
	
	RegistrationFormEnum(IFieldVerifier fv, String[] errorMsg)
	{
		this.fieldVerifier = fv;
		this.errorMessage = errorMsg;
	}

	public IFieldVerifier getVerifier()
	{
		return fieldVerifier;
	}
	
	public String getErrorMessage(int i)
	{
		if( i == 0 ) return "DATA_OK";
		if( 0 < i || i <= errorMessage.length )
		{
			return errorMessage[i-1];
		}
		return null;
	}
	
	public static interface IFieldVerifier
	{
		public <Data> int verifyData(Data data);
	}
	
	public static class DoubleString
	{
		String str1, str2;
		
		public DoubleString(String s1, String s2)
		{
			str1 = s1;
			str2 = s2;
		}
		
		public String getString1()
		{
			return str1;
		}
		
		public String getString2()
		{
			return str2;
		}
		
	}

}
