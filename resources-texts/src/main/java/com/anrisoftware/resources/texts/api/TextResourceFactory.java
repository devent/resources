package com.anrisoftware.resources.texts.api;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Factory to create a new text resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TextResourceFactory {

	/**
	 * Creates a new text resource which will load the text from the URL.
	 * 
	 * @param name
	 *            the name {@link String} of this resource.
	 * 
	 * @param locale
	 *            the {@link Locale} this text resource.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 * 
	 * @param charset
	 *            the {@link Charset} of the resource.
	 * 
	 * @since 1.1
	 */
	TextResource create(String name, Locale locale, URL url, Charset charset);
}
