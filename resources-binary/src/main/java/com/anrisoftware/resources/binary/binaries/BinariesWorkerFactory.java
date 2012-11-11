package com.anrisoftware.resources.binary.binaries;

import java.util.Locale;

import com.anrisoftware.resources.GetBundle;
import com.anrisoftware.resources.binary.api.BundlesMap;

/**
 * Factory to create a new binary resources worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
interface BinariesWorkerFactory {

	/**
	 * Creates a new {@link BinariesWorker}.
	 * 
	 * @param name
	 *            the {@link String} name of the resource we want to get.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource we want to get.
	 * 
	 * @param getBundle
	 *            the {@link GetBundle} that returns the resource bundle for the
	 *            locale.
	 * 
	 * @param bundles
	 *            the map of bundles with their binary resources maps.
	 */
	BinariesWorker create(String name, Locale locale, GetBundle getBundle,
			BundlesMap bundles);
}
