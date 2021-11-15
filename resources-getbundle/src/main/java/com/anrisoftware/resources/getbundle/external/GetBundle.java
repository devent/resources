/*
 * Copyright 2012-2021 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.getbundle.external;


import static java.util.ResourceBundle.getBundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class GetBundle {

	private final String baseName;

	/**
	 * Sets the resource bundle base name.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 */
	public GetBundle(String baseName) {
		this.baseName = baseName;
	}

	/**
	 * Returns the resource bundle for the specified locale.
	 * 
	 * @param locale
	 *            the {@link Locale}.
	 * 
	 * @return the {@link ResourceBundle}.
	 */
	public ResourceBundle bundleFor(Locale locale) {
		return getBundle(baseName, locale);
	}

	/**
	 * Returns the resource bundle base name.
	 * 
	 * @return the bundle base name {@link String}.
	 */
	public String getBaseName() {
		return baseName;
	}

	/**
	 * Returns the resource bundle class loaders.
	 * 
	 * @return the bundle {@link ClassLoader}, or <code>null</code> if no class
	 *         loader was set.
	 */
	public ClassLoader getClassLoader() {
		return getClass().getClassLoader();
	}

	/**
	 * Returns the resource bundle control.
	 * 
	 * @return the bundle {@link ResourceBundle.Control}, or <code>null</code>
	 *         if no control was set.
	 */
	public ResourceBundle.Control getControl() {
		return null;
	}

}
