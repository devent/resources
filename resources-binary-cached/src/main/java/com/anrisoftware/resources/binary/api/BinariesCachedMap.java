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
