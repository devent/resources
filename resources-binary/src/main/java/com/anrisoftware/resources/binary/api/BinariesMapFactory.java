package com.anrisoftware.resources.binary.api;

/**
 * Factory to create a new binary map.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
public interface BinariesMapFactory {

	/**
	 * Creates a new {@link BinariesMap}.
	 */
	BinariesMap create();
}
