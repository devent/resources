package com.anrisoftware.resources.images;

import java.awt.Dimension;
import java.util.Locale;

import com.anrisoftware.resources.images.api.BundlesMap;

/**
 * Factory to create a new images worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
interface ImagesWorkerFactory {

	/**
	 * Creates a new {@link ImagesWorker}.
	 * 
	 * @param name
	 *            the {@link String} name of the image resource we want to get.
	 * 
	 * @param locale
	 *            the {@link Locale} of the image resource we want to get.
	 * 
	 * @param size
	 *            the {@link Dimension} width and height of the image resource
	 *            we want to get.
	 * 
	 * @param getBundle
	 *            the {@link GetBundle} that returns the resource bundle for the
	 *            locale.
	 * 
	 * @param bundles
	 *            the map of bundles with their images maps.
	 */
	ImagesWorker create(String name, Locale locale, Dimension size,
			GetBundle getBundle, BundlesMap bundles);
}
