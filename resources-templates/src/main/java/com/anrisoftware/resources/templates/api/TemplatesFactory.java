/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-api.
 *
 * resources-api is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-api is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-api. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.api;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Factory to create a new templates resources with the specified resource
 * bundle base name and optional class loader and resource bundle control.
 * <p>
 * The class {@link PropertyResourceBundle} is used to load the right property
 * file for the specified locale. The factory offers methods to create template
 * resources with just the base name, or with the class loader or also with the
 * {@link ResourceBundle.Control}.
 * <p>
 * Example:
 * 
 * <pre>
 * TemplatesFactory templatesFactory;
 * Templates templates = templatesFactory.create(&quot;Templates&quot;);
 * // ...
 * Locale locale = Locale.GERMAN;
 * TemplatesResource t = templates.getResource(&quot;hello&quot;, locale);
 * String textString = text.getText(&quot;name&quot;, &quot;Erwin&quot;);
 * System.out.println(textString);
 * 
 * String textString = text.getText(&quot;name&quot;, &quot;Erwin&quot;);
 * System.out.println(textString);
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see Templates
 */
public interface TemplatesFactory {

	/**
	 * Creates a new {@link Templates} with the resource bundle base name and
	 * the caller's class loader.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 */
	Templates create(String baseName);

	/**
	 * Creates a new {@link Templates} with the resource bundle base name and
	 * the class loader.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 * 
	 * @param classLoader
	 *            the {@link ClassLoader}.
	 */
	Templates create(String baseName, ClassLoader classLoader);

	/**
	 * Creates a new {@link Templates} with the resource bundle base name and
	 * the control.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 * 
	 * @param control
	 *            the {@link ResourceBundle.Control}.
	 */
	Templates create(String baseName, ResourceBundle.Control control);

	/**
	 * Creates a new {@link Templates} with the resource bundle base name, the
	 * class loader and the control.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 * 
	 * @param classLoader
	 *            the {@link ClassLoader}.
	 * 
	 * @param control
	 *            the {@link ResourceBundle.Control}.
	 */
	Templates create(String baseName, ClassLoader classLoader,
			ResourceBundle.Control control);

}
