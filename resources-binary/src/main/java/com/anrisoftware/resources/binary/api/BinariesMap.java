package com.anrisoftware.resources.binary.api;

import com.anrisoftware.resources.api.ResourcesException;

/**
 * Puts {@link BinaryResource}s and retrieve them. The resources are identified
 * by their name. Resources with the name that are already in the map are not
 * added.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface BinariesMap {

	/**
	 * <p>
	 * Add new binary resource to the map.
	 * </p>
	 * <p>
	 * If there is already a resource with the same name in the map, the
	 * resource will not be added.
	 * </p>
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
