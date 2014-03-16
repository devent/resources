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

import java.util.ResourceBundle;

/**
 * Associating a resource bundle with an binaries map.
 * <p>
 * Lazy create a new binaries map for a new resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BundlesMap {

	/**
	 * Returns the binaries for the specified resource bundle.
	 * <p>
	 * If no binaries are found for the specified resource bundle a new binaries
	 * map is created.
	 * 
	 * @param baseName
	 *            the base name {@link String} of the resource bundle.
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link BinariesMap} for the resource bundle.
	 */
	BinariesMap getBinaries(String baseName, ResourceBundle bundle);

}
