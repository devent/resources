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

import javax.cache.Cache;
import javax.inject.Inject;

import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.TextsMap;

/**
 * Uses a cache to store the text resources identified by their name.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextsMapImpl implements TextsMap {

	private final TextsMapLogger log;

	private Cache<String, TextResource> cache;

	@Inject
	TextsMapImpl(TextsMapLogger logger) {
		this.log = logger;
	}

	public void setCache(Cache<String, TextResource> cache) {
		this.cache = cache;
	}

	/**
	 * Adds a new text resource to the map. Already added text resource with the
	 * name are discarded.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @param text
	 *            the {@link TextResource} to add.
	 */
	@Override
	public void putText(String name, TextResource text) {
		if (!cache.containsKey(name)) {
			cache.put(name, text);
		} else {
			log.textAlreadyInMap(name);
		}
	}

	/**
	 * Returns the text resource with the name.
	 * 
	 * @param name
	 *            the name of the resource.
	 * 
	 * @return the {@link TextResource} with the name and language, the resource
	 *         with the default language, or <code>null</code>.
	 */
	@Override
	public TextResource getText(String name) {
		return cache.get(name);
	}

	/**
	 * Check if the map contains a text with the name.
	 * 
	 * @param name
	 *            the name of the text.
	 * 
	 * @return <code>true</code> if the map contains the text or
	 *         <code>false</code> if not.
	 */
	@Override
	public boolean haveText(String name) {
		return cache.containsKey(name);
	}
}
