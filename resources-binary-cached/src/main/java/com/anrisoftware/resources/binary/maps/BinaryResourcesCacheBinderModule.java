package com.anrisoftware.resources.binary.maps;

import static com.google.inject.name.Names.named;

import javax.cache.Cache;

import com.anrisoftware.resources.api.BinaryResource;
import com.anrisoftware.resources.binary.api.BinariesCacheKey;
import com.google.inject.AbstractModule;

/**
 * <p>
 * Helper module to bind the cache for the binaries map.
 * </p>
 * <p>
 * It will create a new cache from a specified cache factory.
 * </p>
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
		bind(CacheFactory.class).annotatedWith(
				named(BINARIES_CACHE_FACTORY)).toInstance(cacheFactory);
	}

}
