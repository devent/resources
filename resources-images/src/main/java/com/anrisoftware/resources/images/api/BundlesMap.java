package com.anrisoftware.resources.images.api;

import java.util.ResourceBundle;

/**
 * <p>
 * Associated a resource bundle with an images map.
 * </p>
 * <p>
 * Lazy create a new images map for a new resource bundle.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BundlesMap {

	/**
	 * <p>
	 * Returns the images for the specified resource bundle.
	 * </p>
	 * <p>
	 * If no images are found for the specified resource bundle a new
	 * {@link ImagesMapImpl} is created.
	 * </p>
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link TextsMap} for the resource bundle.
	 */
	ImagesMap getTexts(ResourceBundle bundle);

}
