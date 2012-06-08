package com.anrisoftware.resources.binary.maps;

import static com.google.inject.name.Names.named;

import javax.cache.CacheBuilder;
import javax.cache.CacheManager;

import com.anrisoftware.resources.api.BinaryResource;
import com.google.inject.AbstractModule;

/**
 * <p>
 * Helper module to bind the cache for the binaries map.
 * </p>
 * <p>
 * It will lazy create a new cache from a specified cache builder if the cache
 * is not already present in the cache manager.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 * @see CacheManager
 * @see CacheBuilder
 */
public class BinaryResourcesCacheBinderModule extends AbstractModule {

	public interface BuilderFactory {
		CacheBuilder<String, BinaryResource> create(CacheManager manager,
				String name);
	}

	public static final String BINARIES_MAP_CACHE_NAME = "texts-map-cache";

	public static final String BINARIES_MAP_CACHE_MANAGER = "texts-map-cache-manager";

	public static final String BINARIES_MAP_CACHE_BUILDER = "texts-map-cache-builder";

	private final CacheManager manager;

	private final BuilderFactory builderFactory;

	/**
	 * Set the specified cache builder from which the module will create a new
	 * cache for the binary resources if the cache is not already present in the
	 * specified cache manager.
	 * 
	 * @param manager
	 *            the {@link CacheManager}.
	 * 
	 * @param builder
	 *            the {@link CacheBuilder}.
	 */
	public BinaryResourcesCacheBinderModule(CacheManager manager,
			BuilderFactory builderFactory) {
		this.manager = manager;
		this.builderFactory = builderFactory;
	}

	@Override
	protected void configure() {
		bind(CacheManager.class).annotatedWith(
				named(BINARIES_MAP_CACHE_MANAGER)).toInstance(manager);
		bind(BuilderFactory.class).annotatedWith(
				named(BINARIES_MAP_CACHE_BUILDER)).toInstance(builderFactory);
	}

}
