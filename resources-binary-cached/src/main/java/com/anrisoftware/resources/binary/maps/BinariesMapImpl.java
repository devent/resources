/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anrisoftware.resources.binary.maps;

import java.util.Locale;

import javax.cache.Cache;
import javax.inject.Inject;

import com.anrisoftware.resources.binary.api.BinariesCacheKey;
import com.anrisoftware.resources.binary.api.BinariesCachedMap;
import com.anrisoftware.resources.binary.api.BinaryResource;

/**
 * Uses a JCache cache to store the binary resources identified by their name.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinariesMapImpl implements BinariesCachedMap {

	private final BinariesMapLogger log;

	private Cache<BinariesCacheKey, BinaryResource> cache;

	private String baseName;

	private Locale locale;

	@Inject
	BinariesMapImpl(BinariesMapLogger logger) {
		this.log = logger;
	}

	@Override
	public void setCache(Cache<BinariesCacheKey, BinaryResource> cache) {
		this.cache = cache;
	}

	@Override
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
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
		BinariesCacheKey key = createKey(name);
		if (!cache.containsKey(key)) {
			cache.put(key, resource);
		} else {
			log.textAlreadyInMap(name);
		}
	}

	private CacheKeyImpl createKey(String name) {
		return new CacheKeyImpl(name, baseName, locale);
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
		return cache.get(createKey(name));
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
		return cache.containsKey(createKey(name));
	}
}
