package com.anrisoftware.resources.api;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

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
	 * @param bundle
	 *            the {@link ResourceBundle} this text resource belongs to.
	 * 
	 * @param name
	 *            the name {@link String} of this resource.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 * 
	 * @param charset
	 *            the {@link Charset} of the resource.
	 * 
	 * @since 1.1
	 */
	TextResource create(ResourceBundle bundle, String name, URL url,
			Charset charset);
}
