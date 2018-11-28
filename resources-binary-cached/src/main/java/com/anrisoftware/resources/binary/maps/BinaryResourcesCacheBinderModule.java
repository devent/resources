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

import static com.google.inject.name.Names.named;

import javax.cache.Cache;

import com.anrisoftware.resources.binary.api.BinariesCacheKey;
import com.anrisoftware.resources.binary.api.BinaryResource;
import com.google.inject.AbstractModule;

/**
 * Helper module to bind the cache for the binaries map.
 * <p>
 * It will create a new cache from the specified cache factory.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see CacheFactory
 */
public class BinaryResourcesCacheBinderModule extends AbstractModule {

	public interface CacheFactory {
		Cache<BinariesCacheKey, BinaryResource> create();
	}

	public static final String BINARIES_CACHE_FACTORY = "binaries-cache-factory";

	private final CacheFactory cacheFactory;

	/**
	 * Set the specified cache factory from which we create needed caches.
	 * 
	 * @param factory
	 *            the {@link CacheFactory}.
	 */
	public BinaryResourcesCacheBinderModule(CacheFactory factory) {
		this.cacheFactory = factory;
	}

	@Override
	protected void configure() {
		bind(CacheFactory.class).annotatedWith(named(BINARIES_CACHE_FACTORY))
				.toInstance(cacheFactory);
	}

}
