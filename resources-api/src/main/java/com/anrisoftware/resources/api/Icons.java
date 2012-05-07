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

/**
 * Gives icons resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see ImageResource
 */
public interface Icons {

	/**
	 * Load the resources and returns {@link Icons} instance.
	 * 
	 * @throws ResourcesException
	 *             if some error happening while loading one of the resource.
	 */
	Icons loadResources() throws ResourcesException;

	/**
	 * <p>
	 * Returns the icon resource with the given name and size.
	 * </p>
	 * <p>
	 * If no resource with the given size is available, a resized image will be
	 * created from the nearest available size.
	 * </p>
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param size
	 *            the {@link IconSize} of the resource.
	 * 
	 * @return the {@link ImageResource} for the icon.
	 * 
	 * @throws ResourcesException
	 *             if the resource is not available.
	 */
	ImageResource iconResource(String name, IconSize size)
			throws ResourcesException;

}
