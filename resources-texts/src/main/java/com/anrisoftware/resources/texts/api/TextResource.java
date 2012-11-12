package com.anrisoftware.resources.texts.api;

import java.nio.charset.Charset;
import java.util.Locale;

import com.anrisoftware.resources.api.Resource;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Text resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see TextResourceFactory
 */
public interface TextResource extends Resource {

	/**
	 * Returns the character set of this resource.
	 * 
	 * @return the {@link Charset}.
	 * 
	 * @since 1.1
	 */
	Charset getCharset();

	/**
	 * Returns the text {@link String} of the resource.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 */
	String getText() throws ResourcesException;

	/**
	 * Returns a formatted text of the resource.
	 * <p>
	 * The string is formatted as in
	 * {@link String#format(Locale, String, Object...)} with the current text as
	 * the format string and the current locale as the locale.
	 * 
	 * @param args
	 *            the arguments.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 * 
	 * @since 1.2
	 */
	String getFormattedText(Object... args) throws ResourcesException;

}
