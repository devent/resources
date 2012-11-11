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
