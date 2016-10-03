/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anrisoftware.resources.templates.external;

import java.util.Locale;
import java.util.ResourceBundle;

import com.anrisoftware.resources.external.ResourcesException;

/**
 * Gives template resources. Template resources are processed with a given data.
 * The processed template is cached and the same text is returned for the same
 * data.
 * <p>
 * An instance is usually created from the {@link TemplatesFactory} factory, in
 * which we can set the base name and optionally the class loader and the
 * control. The parameters are passed to the {@link ResourceBundle} class to
 * load the resource property file.
 * <p>
 * Example after instantiating the type:
 * 
 * <pre>
 * Templates templates;
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
	 * Returns the template resource with the given name and the default locale
	 * as in {@link Locale#getDefault()}.
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
	 * Returns the template resource with the given name and language.
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
