package com.anrisoftware.resources.binary.api;

import java.util.ResourceBundle;

import com.anrisoftware.resources.binary.maps.DefaultBinariesMap;

/**
 * <p>
 * Associated a resource bundle with an binaries map.
 * </p>
 * <p>
 * Lazy create a new binaries map for a new resource bundle.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BundlesMap {

	/**
	 * <p>
	 * Returns the binaries for the specified resource bundle.
	 * </p>
	 * <p>
	 * If no binaries are found for the specified resource bundle a new
	 * {@link DefaultBinariesMap} is created.
	 * </p>
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link TextsMap} for the resource bundle.
	 */
	BinariesMap getBinaries(ResourceBundle bundle);

}
