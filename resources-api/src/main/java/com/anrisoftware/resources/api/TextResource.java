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
	 * Returns a formatted text {@link String} of the resource. The string is
	 * formatted as in {@link String#format(Locale, String, Object...)} with the
	 * language as the locale.
	 * 
	 * @param args
	 *            the arguments.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 */
	String formatText(Object... args) throws ResourcesException;

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
