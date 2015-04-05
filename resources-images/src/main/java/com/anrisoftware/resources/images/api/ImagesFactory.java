/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-images.
 *
 * resources-images is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-images is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-images. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.images.api;

import java.util.ResourceBundle;

/**
 * Factory to create a new images resources with the specified resource bundle
 * base name and optional class loader and resource bundle control.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 * @see Images
 */
public interface ImagesFactory {

	/**
	 * Creates a new {@link Images} with the resource bundle base name and the
	 * caller's class loader.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 */
	Images create(String baseName);

	/**
	 * Creates a new {@link Images} with the resource bundle base name and the
	 * class loader.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 * 
	 * @param classLoader
	 *            the {@link ClassLoader}.
	 */
	Images create(String baseName, ClassLoader classLoader);

	/**
	 * Creates a new {@link Images} with the resource bundle base name and the
	 * control.
	 * 
	 * @param baseName
	 *            the base name {@link String}.
	 * 
	 * @param control
	 *            the {@link ResourceBundle.Control}.
	 */
	Images create(String baseName, ResourceBundle.Control control);

	/**
	 * Creates a new {@link Images} with the resource bundle base name, the
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
	Images create(String baseName, ClassLoader classLoader,
			ResourceBundle.Control control);

}
