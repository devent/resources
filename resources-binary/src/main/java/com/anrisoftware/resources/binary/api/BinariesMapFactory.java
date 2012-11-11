package com.anrisoftware.resources.binary.api;

/**
 * Factory to create a new map for binary resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BinariesMapFactory {

	/**
	 * Creates a new map for binary resources.
	 * 
	 * @return the {@link BinariesMap}.
	 */
	BinariesMap create();
}
