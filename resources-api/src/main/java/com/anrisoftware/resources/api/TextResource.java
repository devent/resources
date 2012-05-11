package com.anrisoftware.resources.api;

import java.net.URL;
import java.util.Locale;

/**
 * Text resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see TextResourceFactory
 */
public interface TextResource {

	/**
	 * Returns the text {@link String} of the resource.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 */
	String getText() throws ResourcesException;

	/**
	 * Returns the language {@link Locale} of the resource or <code>null</code>
	 * if it is the default language.
	 */
	Locale getLanguage();

	/**
	 * Returns the {@link URL} of the resource.
	 */
	URL getURL();
}
