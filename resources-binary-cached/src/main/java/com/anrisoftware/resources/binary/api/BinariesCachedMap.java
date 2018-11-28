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
package com.anrisoftware.resources.binary.api;

import java.util.Locale;

import javax.cache.Cache;

/**
 * Uses a cache to store the binary resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface BinariesCachedMap extends BinariesMap {

	/**
	 * Sets the cache to be used to store the binary resources.
	 * 
	 * @param cache
	 *            the {@link Cache}.
	 */
	void setCache(Cache<BinariesCacheKey, BinaryResource> cache);

	/**
	 * Sets the resource bundle base name. It is used to identify the cache
	 * entries for the binary resources.
	 * 
	 * @param baseName
	 *            the {@link String} base name.
	 */
	void setBaseName(String baseName);

	/**
	 * Sets the resource bundle locale. It is used to identify the cache entries
	 * for the binary resources.
	 * 
	 * @param locale
	 *            the {@link Locale}.
	 */
	void setLocale(Locale locale);

}
