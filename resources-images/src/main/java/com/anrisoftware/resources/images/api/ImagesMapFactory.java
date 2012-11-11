package com.anrisoftware.resources.images.api;

/**
 * Factory to create a new images map.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface ImagesMapFactory {

	/**
	 * Creates a new images map.
	 * 
	 * @return the {@link ImagesMap}.
	 */
	ImagesMap create();
}
