
package com.anrisoftware.resources.binary.external;

/*-
 * #%L
 * Resources :: Binary
 * %%
 * Copyright (C) 2012 - 2018 Advanced Natural Research Institute
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.anrisoftware.resources.api.external.ResourcesException;

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