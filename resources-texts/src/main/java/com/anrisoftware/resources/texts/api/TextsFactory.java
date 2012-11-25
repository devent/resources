/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-texts.
 *
 * resources-texts is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-texts is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-texts. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.texts.api;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * <p>
 * Factory to create a new texts resources with the specified resource bundle
 * base name and optional class loader and resource bundle control.
 * <p>
 * The class {@link PropertyResourceBundle} is used to load the right property
 * file for the specified locale. The factory offers methods to create text
 * resources with just the base name, or with the class loader or also with the
 * {@link ResourceBundle.Control}.
 * 
 * <pre>
 * resourceModule = new TextsResourcesModule()
 * mapsModule = new TextsDefaultMapsModule()
 * charsetModule = new TextsResourcesCharsetModule()
 * binariesModule = new BinariesResourcesModule()
 * binariesMapsModule = new BinariesDefaultMapsModule()
 * List modules = { textsResourceModule, mapsModule, charsetModule, binariesModule, binariesMapsModule }
 * Injector injector = Guice.createInjector(modules);
 * TextsFactory factory = injector.getInstance(TextsFactory);
 * 
 * // ...
 * String baseName = "TextsWithDefaultCharset";
 * Texts texts = factory.create(baseName);
 * 
 * // ...
 * Locale locale = Locale.GERMAN;
 * TextResource text = texts.getResource("hello", locale);
 * String textString = text.getText();
 * System.out.println(textString);
 * 
 * // ...
 * TextResource text = texts.getResource("withplaceholders");
 * String textString = text.getFormattedText("text", 10, 0.333);
 * System.out.println(textString);
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 * @see Texts
 */
public interface TextsFactory {

	/**
	 * Creates a new {@link Texts} with the resource bundle base name and the
	 * caller's class loader.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 */
	Texts create(String baseName);

	/**
	 * Creates a new {@link Texts} with the resource bundle base name and the
	 * class loader.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 * 
	 * @param classLoader
	 *            the {@link ClassLoader}.
	 */
	Texts create(String baseName, ClassLoader classLoader);

	/**
	 * Creates a new {@link Texts} with the resource bundle base name and the
	 * control.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 * 
	 * @param control
	 *            the {@link ResourceBundle.Control}.
	 */
	Texts create(String baseName, ResourceBundle.Control control);

	/**
	 * Creates a new {@link Texts} with the resource bundle base name, the class
	 * loader and the control.
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
	Texts create(String baseName, ClassLoader classLoader,
			ResourceBundle.Control control);

}
