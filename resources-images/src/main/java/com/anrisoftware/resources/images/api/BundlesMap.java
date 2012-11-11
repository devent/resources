package com.anrisoftware.resources.images.api;

import java.util.ResourceBundle;

/**
 * Associating a resource bundle with an images map.
 * <p>
 * Lazy create a new images map for a new resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BundlesMap {

	/**
	 * Returns the images for the specified resource bundle.
	 * <p>
	 * If no images are found for the specified resource bundle a new images map
	 * is created.
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link ImagesMap} for the resource bundle.
	 */
	ImagesMap getImages(ResourceBundle bundle);

}
