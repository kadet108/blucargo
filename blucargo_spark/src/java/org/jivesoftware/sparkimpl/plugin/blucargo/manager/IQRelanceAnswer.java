package org.jivesoftware.sparkimpl.plugin.blucargo.manager;

import org.jivesoftware.smack.packet.IQ;

public class IQRelanceAnswer extends IQ {

	private boolean IsOk;

	public boolean isIsOk() {

		return IsOk;

	}

	public void setIsOk(boolean IsOk) {

		this.IsOk = IsOk;

	}

	public void setIsOk(String IsOk) {

		this.IsOk = Boolean.getBoolean(IsOk);

	}

	@Override
	public String getChildElementXML() {

		return null;

	}

}