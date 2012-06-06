package com.anrisoftware.resources.api;

import java.net.URL;
import java.util.Locale;

/**
 * Resource for binary data.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 */
public interface BinaryResource {

	/**
	 * Returns the binary data of the resource.
	 * 
	 * @return a byte array of the data.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 */
	byte[] getBinary() throws ResourcesException;

	/**
	 * Returns the name of this resource.
	 * 
	 * @return the {@link String} name.
	 */
	String getName();

	/**
	 * Returns the locale of the resource.
	 * 
	 * @return the {@link Locale} of the resource.
	 */
	Locale getLocale();

	/**
	 * Returns the URL of the resource.
	 * 
	 * @return the {@link URL} of the resource.
	 */
	URL getURL();
}
