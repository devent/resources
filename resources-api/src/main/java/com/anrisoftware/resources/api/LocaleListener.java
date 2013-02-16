package com.anrisoftware.resources.api;

import java.util.Locale;

/**
 * Listens for changes in the current locale.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.6
 */
public interface LocaleListener {

	/**
	 * Inform the listener about the change in the current locale.
	 * 
	 * @param locale
	 *            the new {@link Locale}.
	 */
	void updateLocale(Locale locale);
}
