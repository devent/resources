package com.anrisoftware.resources.api;

import java.util.Properties;

/**
 * <p>
 * Template resource.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.2
 * @see TemplateResourceFactory
 */
public interface TemplateResource extends Resource {

	/**
	 * Returns the properties of this resource.
	 * 
	 * @return the {@link Properties}.
	 */
	Properties getProperties();

	/**
	 * <p>
	 * Process the template and returns the text.
	 * </p>
	 * <p>
	 * The processed text is cached and is returned for the same data.
	 * </p>
	 * 
	 * @param data
	 *            the data.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 */
	String getText(Object... data) throws ResourcesException;
}
