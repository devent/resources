/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-templates.
 *
 * resources-templates is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-templates is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-templates. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.api;

import java.io.Serializable;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
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
	 * @param attributes
	 *            the attributes {@link Map} for the template.
	 * 
	 * @return the new {@link TemplateResource}.
	 * 
	 * @since 1.4
	 */
	TemplateResource create(String name, Locale locale, URL url,
			Properties properties, Map<Serializable, Serializable> attributes);
}
