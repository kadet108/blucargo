package org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class StrAccessor {
	private static final String BUNDLE_NAME = "org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.customStrings"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private StrAccessor() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	public static void main(String[] args){
		
	}
}
