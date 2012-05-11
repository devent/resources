package com.anrisoftware.resources.api;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Factory to create a new {@link TextResource}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TextResourceFactory {

	/**
	 * Creates a new {@link TextResource} which will load the text from the url.
	 * 
	 * @param locale
	 *            the language {@link Locale} of the resource.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 * 
	 * @param charset
	 *            the {@link Charset} of the resource.
	 */
	TextResource create(Locale locale, URL url, Charset charset);
}
