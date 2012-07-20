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
package com.anrisoftware.resources.api;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>
 * Gives template resources. Template resources are processed with a given data.
 * The processed template is cached and the same text is returned for the same
 * data.
 * </p>
 * <p>
 * An instance is usually created from the {@link TemplatesFactory} factory, in
 * which we can set the base name and optionally the class loader and the
 * control. The parameters are passed to the {@link ResourceBundle} class to
 * load the resource property file.
 * </p>
 * <p>
 * Example after instantiating the type:
 * </p>
 * 
 * <pre>
 * Templates templates;
 * 
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
 * @since 1.2
 * @see TemplateResource
 * @see TemplatesFactory
 */
public interface Templates {

	/**
	 * Returns the used resource bundle base name.
	 * 
	 * @return the used base name {@link String}.
	 */
	String getBaseName();

	/**
	 * Returns the used resource bundle class loader.
	 * 
	 * @return the used {@link ClassLoader}.
	 */
	ClassLoader getClassLoader();

	/**
	 * Returns the used resource bundle control.
	 * 
	 * @return the used {@link ResourceBundle.Control}.
	 */
	ResourceBundle.Control getControl();

	/**
	 * <p>
	 * Returns the template resource with the given name and the default locale
	 * as in {@link Locale#getDefault()}.
	 * </p>
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return the {@link TemplateResource}.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 */
	TemplateResource getResource(String name) throws ResourcesException;

	/**
	 * <p>
	 * Returns the template resource with the given name and language.
	 * </p>
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @return the {@link TemplateResource}.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 */
	TemplateResource getResource(String name, Locale locale)
			throws ResourcesException;

}
