package com.anrisoftware.resources.texts.api;

/**
 * Factory to create a new texts map.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface TextsMapFactory {

	/**
	 * Creates a new {@link TextsMap}.
	 */
	TextsMap create();
}
