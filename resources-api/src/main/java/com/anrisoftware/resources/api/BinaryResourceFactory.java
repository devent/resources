package com.anrisoftware.resources.api;

import java.net.URL;
import java.util.Locale;

/**
 * Factory to create a new binary data resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 */
public interface BinaryResourceFactory {

	/**
	 * Creates a new {@link BinaryResource} which will load the text from the
	 * URL.
	 * 
	 * @param name
	 *            the name {@link String} of this resource.
	 * 
	 * @param locale
	 *            the {@link Locale} this text resource.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 */
	BinaryResource create(String name, Locale locale, URL url);
}
