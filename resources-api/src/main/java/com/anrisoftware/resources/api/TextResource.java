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
	 * <p>
	 * Returns a formatted text {@link String} of the resource.
	 * </p>
	 * <p>
	 * The string is formatted as in
	 * {@link String#format(Locale, String, Object...)} with the current text as
	 * the format string and the current locale as the locale.
	 * </p>
	 * 
	 * @param args
	 *            the arguments.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 */
	String formatText(Object... args) throws ResourcesException;

	/**
	 * Returns the locale of the resource.
	 * 
	 * @return the {@link Locale} of the resource.
	 * 
	 * @since 1.1
	 */
	Locale getLocale();

	/**
	 * Returns the {@link URL} of the resource.
	 */
	URL getURL();
}
