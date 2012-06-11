package com.anrisoftware.resources.api;

import java.net.URL;
import java.util.Locale;

/**
 * Base interface for a resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface Resource {

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
