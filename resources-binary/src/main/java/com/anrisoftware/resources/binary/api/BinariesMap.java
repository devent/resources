/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import com.anrisoftware.resources.api.ResourcesException;

/**
 * Puts binary resources and retrieve them. The resources are identified by
 * their name. Resources with the name that are already in the map are not
 * added.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface BinariesMap {

	/**
	 * Add new binary resource to the map.
	 * <p>
	 * If there is already a resource with the same name in the map, the
	 * resource will not be added.
	 * 
	 * @param resource
	 *            the {@link BinaryResource} that should be added.
	 */
	void putBinary(BinaryResource resource) throws ResourcesException;

	/**
	 * Returns the binary resource from the map with the specified name.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return the {@link BinaryResource}, or <code>null</code> if no such
	 *         resource was found in the map.
	 */
	BinaryResource getBinary(String name);

	/**
	 * Check if the binary resource with the specified name is in the map.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return <code>true</code> if the resource is in the map,
	 *         <code>false</code> otherwise.
	 */
	boolean haveBinary(String name);

}
