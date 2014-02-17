package org.mozilla.browser.jnlp;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class mt {
	private static final String BUNDLE_NAME = "org.mozilla.browser.jnlp.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private mt() {
	}

	public static String t(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
