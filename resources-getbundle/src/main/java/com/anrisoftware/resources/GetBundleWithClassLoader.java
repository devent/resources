/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-getbundle.
 *
 * resources-getbundle is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-getbundle is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-getbundle. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources;

import static java.util.ResourceBundle.getBundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Returns the resource bundle with the specified base name and class loader.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class GetBundleWithClassLoader extends GetBundle {

	private final ClassLoader classLoader;

	/**
	 * Sets the resource bundle the base name and class loader.
	 * 
	 * @param baseName
	 *            the bundle base name {@link String}.
	 * 
	 * @param classLoader
	 *            the bundle {@link ClassLoader}.
	 */
	public GetBundleWithClassLoader(String baseName, ClassLoader classLoader) {
		super(baseName);
		this.classLoader = classLoader;
	}

	@Override
	public ResourceBundle bundleFor(Locale locale) {
		return getBundle(getBaseName(), locale, classLoader);
	}

	@Override
	public ClassLoader getClassLoader() {
		return classLoader;
	}
}
