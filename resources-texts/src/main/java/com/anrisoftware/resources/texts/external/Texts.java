/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.texts.external;

import java.util.Locale;
import java.util.ResourceBundle;

import com.anrisoftware.resources.external.ResourcesException;

/**
 * Gives text resources. Text resources are either single words, line or whole
 * text.
 * <p>
 * An instance is usually created from the {@link TextsFactory} factory, in
 * which we can set the base name and optionally the class loader and the
 * control. The parameters are passed to the {@link ResourceBundle} class to
 * load the resource property file.
 * <p>
 * Example after instantiating the type:
 * 
 * <pre>
 * Texts texts;
 * 
 * // ...
 * Locale locale = Locale.GERMAN;
 * TextResource text = texts.getResource(&quot;hello&quot;, locale);
 * String textString = text.getText();
 * System.out.println(textString);
 * 
 * // ...
 * TextResource text = texts.getResource(&quot;withplaceholders&quot;);
 * String textString = text.getFormattedText(&quot;text&quot;, 10, 0.333);
 * System.out.println(textString);
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see TextResource
 * @see TextsFactory
 */
public interface Texts {

	/**
	 * Returns the used resource bundle base name.
	 * 
	 * @return the used base name {@link String}.
	 * 
	 * @since 1.1
	 */
	String getBaseName();

	/**
	 * Returns the used resource bundle class loader.
	 * 
	 * @return the used {@link ClassLoader}.
	 * 
	 * @since 1.1
	 */
	ClassLoader getClassLoader();

	/**
	 * Returns the used resource bundle control.
	 * 
	 * @return the used {@link ResourceBundle.Control}.
	 * 
	 * @since 1.1
	 */
	ResourceBundle.Control getControl();

	/**
	 * <p>
	 * Returns the text resource with the given name and the default locale as
	 * in {@link Locale#getDefault()}.
	 * </p>
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return the {@link TextResource}.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.2
	 */
	TextResource getResource(String name) throws ResourcesException;

	/**
	 * <p>
	 * Returns the text resource with the given name and language.
	 * </p>
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource.
	 * 
	 * @return the {@link TextResource}.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.2
	 */
	TextResource getResource(String name, Locale locale)
			throws ResourcesException;

}
