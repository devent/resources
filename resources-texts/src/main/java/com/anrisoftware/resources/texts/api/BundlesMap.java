package com.anrisoftware.resources.texts.api;

import java.util.ResourceBundle;

/**
 * <p>
 * A map of texts for each resource bundle.
 * </p>
 * <p>
 * Lazy create a new texts map for a new resource bundle.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BundlesMap {

	/**
	 * <p>
	 * Returns the texts for the specified resource bundle.
	 * </p>
	 * <p>
	 * If no texts are found for the specified resource bundle a new
	 * {@link TextsMap} is created.
	 * </p>
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link TextsMap} for the resource bundle.
	 */
	TextsMap getTexts(ResourceBundle bundle);

}
