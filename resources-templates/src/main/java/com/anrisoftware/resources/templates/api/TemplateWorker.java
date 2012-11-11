package com.anrisoftware.resources.templates.api;

import java.io.Serializable;
import java.net.URL;
import java.util.Properties;

import com.anrisoftware.resources.api.ResourcesException;

/**
 * Process the calculated data and create a string for output.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TemplateWorker extends Serializable {

	/**
	 * Returns the URL of the template.
	 * 
	 * @return the {@link URL} of the template.
	 */
	URL getURL();

	/**
	 * Returns the properties of the template.
	 * 
	 * @return the {@link Properties} of the template.
	 */
	Properties getProperties();

	/**
	 * Process the template and return the text.
	 * 
	 * @param name
	 *            the name of the template.
	 * 
	 * @param data
	 *            the data.
	 * 
	 * @return the processed template as a string.
	 * 
	 * @throws ResourcesException
	 *             if an error has occurred while processing the template.
	 */
	String process(String name, Object... data) throws ResourcesException;

}
