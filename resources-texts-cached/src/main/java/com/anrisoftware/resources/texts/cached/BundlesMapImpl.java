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

import static com.anrisoftware.resources.texts.cached.TextsResourcesCacheModule.TEXTS_MAP_CACHE_BUILDER;
import static com.anrisoftware.resources.texts.cached.TextsResourcesCacheModule.TEXTS_MAP_CACHE_MANAGER;
import static com.anrisoftware.resources.texts.cached.TextsResourcesCacheModule.TEXTS_MAP_CACHE_NAME;
import static java.lang.String.format;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.inject.Inject;
import javax.inject.Named;

import com.anrisoftware.resources.texts.api.BundlesMap;
import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.TextsMap;
import com.anrisoftware.resources.texts.api.TextsMapFactory;
import com.anrisoftware.resources.texts.cached.TextsResourcesCacheModule.BuilderFactory;
import com.google.common.collect.Maps;

/**
 * Uses a Java hash map to store the texts for each resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BundlesMapImpl implements BundlesMap {

	private final BundlesMapLogger log;

	private final Map<ResourceBundle, TextsMap> map;

	private final TextsMapFactory textsFactory;

	private final CacheManager manager;

	private final BuilderFactory builderFactory;

	@Inject
	BundlesMapImpl(BundlesMapLogger logger, TextsMapFactory textsFactory,
			@Named(TEXTS_MAP_CACHE_MANAGER) CacheManager manager,
			@Named(TEXTS_MAP_CACHE_BUILDER) BuilderFactory builderFactory) {
		this.log = logger;
		this.map = Maps.newHashMap();
		this.textsFactory = textsFactory;
		this.manager = manager;
		this.builderFactory = builderFactory;
	}

	@Override
	public TextsMap getTexts(ResourceBundle bundle) {
		TextsMap texts = map.get(bundle);
		if (texts == null) {
			texts = createTexts(bundle);
		}
		return texts;
	}

	private TextsMap createTexts(ResourceBundle bundle) {
		TextsMapImpl texts = (TextsMapImpl) textsFactory.create();
		Cache<String, TextResource> cache = lazyCreateCache(bundle.getLocale());
		texts.setCache(cache);
		map.put(bundle, texts);
		return texts;
	}

	private Cache<String, TextResource> lazyCreateCache(Locale locale) {
		String name = format("%s-%s", TEXTS_MAP_CACHE_NAME, locale);
		Cache<String, TextResource> cache = manager.getCache(name);
		if (cache == null) {
			cache = builderFactory.create(manager, name).build();
			log.buildNewCache(name);
		}
		return cache;
	}
}
