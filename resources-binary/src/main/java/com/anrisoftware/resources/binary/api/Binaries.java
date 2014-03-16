/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-binary.
 *
 * resources-binary is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-binary is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-binary. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.binary.api;

import java.util.Locale;
import java.util.ResourceBundle;

import com.anrisoftware.resources.api.ResourcesException;

/**
 * Gives binary data resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 * @see BinaryResource
 */
public interface Binaries {

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
	 * Returns the binary resource with the specified name and language.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param locale
	 *            the {@link Locale} of the resource or <code>null</code>. If
	 *            the locale is <code>null</code> the default locale as in
	 *            {@link Locale#getDefault()} is used.
	 * 
	 * @return the {@link BinaryResource}.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.2
	 */
	BinaryResource getResource(String name, Locale locale)
			throws ResourcesException;

	/**
	 * Returns the binary resource with the specified name and the default
	 * locale as in {@link Locale#getDefault()}.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return the {@link BinaryResource}.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 * 
	 * @since 1.2
	 */
	BinaryResource getResource(String name) throws ResourcesException;

}
