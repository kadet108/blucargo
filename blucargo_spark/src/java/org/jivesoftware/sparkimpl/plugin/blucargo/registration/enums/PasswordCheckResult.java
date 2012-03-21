package org.jivesoftware.sparkimpl.plugin.blucargo.registration.enums;

public enum PasswordCheckResult {
	SHORT_PASSWORD("Pole has\u0142o musi by\u0107 wype\u0142nione."),
	NUMBER_COUNT_ERROR("Has\u0142o musi posiada\u0107 cyfr\u0119"),
	UPPERCASE_COUNT_ERROR("Has\u0142o musi posiada\u0107 du\u017c\u0105 liter\u0119"),
	PASSWORDS_DO_NOT_MATCH("Has\u0142a nie pasuj\u0105 do siebia."),
	OK_PASSWORD("ok");
	
	String info;

	PasswordCheckResult(String info)
	{
		this.info = info;
	}
	
	public String getInfo()
	{
		return info;
	}
}
