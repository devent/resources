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
 * Returns a resource bundle for the given base name and the caller's class
 * loader.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
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
