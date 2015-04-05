/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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
 * Returns the resource bundle with the specified base name, class loader and
 * control.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class GetBundleWithClassLoaderAndControl extends
		GetBundleWithClassLoader {

	private final ResourceBundle.Control control;

	/**
	 * Sets the resource bundle the base name and class loader.
	 * 
	 * @param baseName
	 *            the bundle base name {@link String}.
	 * 
	 * @param classLoader
	 *            the bundle {@link ClassLoader}.
	 * 
	 * @param control
	 *            the bundle {@link ResourceBundle.Control}.
	 */
	public GetBundleWithClassLoaderAndControl(String baseName,
			ClassLoader classLoader, ResourceBundle.Control control) {
		super(baseName, classLoader);
		this.control = control;
	}

	@Override
	public ResourceBundle bundleFor(Locale locale) {
		return getBundle(getBaseName(), locale, getClassLoader(), control);
	}

	@Override
	public ResourceBundle.Control getControl() {
		return control;
	}

}
