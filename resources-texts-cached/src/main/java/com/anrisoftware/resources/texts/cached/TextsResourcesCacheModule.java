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
package com.anrisoftware.resources.texts.cached;

import static com.google.inject.name.Names.named;

import javax.cache.CacheBuilder;
import javax.cache.CacheManager;

import com.anrisoftware.resources.texts.api.TextResource;
import com.google.inject.AbstractModule;

/**
 * Helper module to bind the cache for the texts map.
 * <p>
 * It will lazy create a new cache from a specified cache builder if the cache
 * is not already present in the cache manager.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see CacheManager
 * @see CacheBuilder
 */
public class TextsResourcesCacheModule extends AbstractModule {

	public interface BuilderFactory {
		CacheBuilder<String, TextResource> create(CacheManager manager,
				String name);
	}

	public static final String TEXTS_MAP_CACHE_NAME = "texts-map-cache";

	public static final String TEXTS_MAP_CACHE_MANAGER = "texts-map-cache-manager";

	public static final String TEXTS_MAP_CACHE_BUILDER = "texts-map-cache-builder";

	private final CacheManager manager;

	private final BuilderFactory builderFactory;

	/**
	 * Set the specified cache builder from which the module will create a new
	 * cache for the texts map if the cache is not already present in the
	 * specified cache manager.
	 * 
	 * @param manager
	 *            the {@link CacheManager}.
	 * 
	 * @param builder
	 *            the {@link CacheBuilder}.
	 */
	public TextsResourcesCacheModule(CacheManager manager,
			BuilderFactory builderFactory) {
		this.manager = manager;
		this.builderFactory = builderFactory;
	}

	@Override
	protected void configure() {
		bind(CacheManager.class).annotatedWith(named(TEXTS_MAP_CACHE_MANAGER))
				.toInstance(manager);
		bind(BuilderFactory.class)
				.annotatedWith(named(TEXTS_MAP_CACHE_BUILDER)).toInstance(
						builderFactory);
	}

}
