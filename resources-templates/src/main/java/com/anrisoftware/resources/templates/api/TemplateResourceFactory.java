package com.anrisoftware.resources.templates.api;

import java.net.URL;
import java.util.Locale;
import java.util.Properties;

/**
 * Factory to create a new template resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TemplateResourceFactory {

	/**
	 * Creates a new template resource with the specified name, locale, URL and
	 * template properties.
	 * 
	 * @param name
	 *            the name {@link String} of the template.
	 * 
	 * @param locale
	 *            the {@link Locale} the template.
	 * 
	 * @param url
	 *            the {@link URL} of the resource.
	 * 
	 * @param properties
	 *            the template {@link Properties} which specify additional
	 *            parameter for the template.
	 * 
	 * @return the new {@link TemplateResource}.
	 */
	TemplateResource create(String name, Locale locale, URL url,
			Properties properties);
}
