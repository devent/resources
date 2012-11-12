package com.anrisoftware.resources.texts.api;

import java.util.ResourceBundle;

/**
 * A map of texts for each resource bundle.
 * <p>
 * Lazy create a new texts map for a new resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BundlesMap {

	/**
	 * Returns the texts for the specified resource bundle.
	 * <p>
	 * If no texts are found for the specified resource bundle a new texts map
	 * is created.
	 * 
	 * @param bundle
	 *            the {@link ResourceBundle}.
	 * 
	 * @return the {@link TextsMap} for the resource bundle.
	 */
	TextsMap getTexts(ResourceBundle bundle);

}
