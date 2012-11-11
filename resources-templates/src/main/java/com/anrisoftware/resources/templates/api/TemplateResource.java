package com.anrisoftware.resources.templates.api;

import java.util.Properties;

import com.anrisoftware.resources.api.Resource;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Template resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see TemplateResourceFactory
 */
public interface TemplateResource extends Resource {

	/**
	 * Process the template and returns the text.
	 * <p>
	 * The processed text is cached and is returned for the same data.
	 * 
	 * @param data
	 *            the data for the template.
	 * 
	 * @throws ResourcesException
	 *             if there was an error loading the text.
	 */
	String getText(Object... data) throws ResourcesException;

	/**
	 * Invalidate the template resource. Delete the cached resource so a
	 * subsequent call of {@link #getText(Object...)} will process the template.
	 * <p>
	 * Use this if the data for the template have changed.
	 */
	void invalidate();

	/**
	 * Returns the properties of the template resource.
	 * 
	 * @return the {@link Properties}.
	 */
	Properties getProperties();
}
