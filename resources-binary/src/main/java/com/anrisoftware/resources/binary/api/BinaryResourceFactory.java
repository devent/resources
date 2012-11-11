package com.anrisoftware.resources.binary.api;

import java.net.URL;
import java.util.Locale;

/**
 * Factory to create a new binary data resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BinaryResourceFactory {

	/**
	 * Creates a new binary resource with the specified name, locale and URL.
	 * 
	 * @param name
	 *            the name {@link String} of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} this the resource.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 */
	BinaryResource create(String name, Locale locale, URL url);
}
