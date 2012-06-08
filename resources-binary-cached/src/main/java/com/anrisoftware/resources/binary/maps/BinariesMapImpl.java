package com.anrisoftware.resources.binary.maps;

import javax.cache.Cache;
import javax.inject.Inject;

import com.anrisoftware.resources.api.BinaryResource;
import com.anrisoftware.resources.binary.api.BinariesMap;

/**
 * <p>
 * Uses a JCache cache to store the binary resources identified by their name.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinariesMapImpl implements BinariesMap {

	private final BinariesMapLogger log;

	private Cache<String, BinaryResource> cache;

	@Inject
	BinariesMapImpl(BinariesMapLogger logger) {
		this.log = logger;
	}

	public void setCache(Cache<String, BinaryResource> cache) {
		this.cache = cache;
	}

	/**
	 * Adds a new binary resource to the map. Already added binary resource with
	 * the name are discarded.
	 * 
	 * @param resource
	 *            the {@link BinaryResource} to add.
	 */
	@Override
	public void putBinary(BinaryResource resource) {
		String name = resource.getName();
		if (!cache.containsKey(name)) {
			cache.put(name, resource);
		} else {
			log.textAlreadyInMap(name);
		}
	}

	/**
	 * Returns the binary resource with the name.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return the {@link BinaryResource} with the name and language, the
	 *         resource with the default language, or <code>null</code>.
	 */
	@Override
	public BinaryResource getBinary(String name) {
		return cache.get(name);
	}

	/**
	 * Check if the map contains a binary with the name.
	 * 
	 * @param name
	 *            the name of the binary.
	 * 
	 * @return <code>true</code> if the map contains the binary or
	 *         <code>false</code> if not.
	 */
	@Override
	public boolean haveBinary(String name) {
		return cache.containsKey(name);
	}
}
