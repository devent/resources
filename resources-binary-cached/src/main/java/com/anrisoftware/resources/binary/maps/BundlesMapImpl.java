package com.anrisoftware.resources.binary.maps;

import static com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.BINARIES_MAP_CACHE_BUILDER;
import static com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.BINARIES_MAP_CACHE_MANAGER;
import static com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.BINARIES_MAP_CACHE_NAME;
import static java.lang.String.format;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.inject.Inject;
import javax.inject.Named;

import com.anrisoftware.resources.api.BinaryResource;
import com.anrisoftware.resources.binary.api.BinariesMap;
import com.anrisoftware.resources.binary.api.BinariesMapFactory;
import com.anrisoftware.resources.binary.api.BundlesMap;
import com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.BuilderFactory;
import com.google.common.collect.Maps;

/**
 * <p>
 * Uses a {@link HashMap} to store the binaries for each resource bundle.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BundlesMapImpl implements BundlesMap {

	private final BundlesMapLogger log;

	private final Map<ResourceBundle, BinariesMap> map;

	private final BinariesMapFactory textsFactory;

	private final CacheManager manager;

	private final BuilderFactory builderFactory;

	@Inject
	BundlesMapImpl(BundlesMapLogger logger, BinariesMapFactory textsFactory,
			@Named(BINARIES_MAP_CACHE_MANAGER) CacheManager manager,
			@Named(BINARIES_MAP_CACHE_BUILDER) BuilderFactory builderFactory) {
		this.log = logger;
		this.map = Maps.newHashMap();
		this.textsFactory = textsFactory;
		this.manager = manager;
		this.builderFactory = builderFactory;
	}

	@Override
	public BinariesMap getBinaries(ResourceBundle bundle) {
		BinariesMap Binaries = map.get(bundle);
		if (Binaries == null) {
			Binaries = createTexts(bundle);
		}
		return Binaries;
	}

	private BinariesMap createTexts(ResourceBundle bundle) {
		BinariesMapImpl binaries = (BinariesMapImpl) textsFactory.create();
		Cache<String, BinaryResource> cache = lazyCreateCache(bundle
				.getLocale());
		binaries.setCache(cache);
		map.put(bundle, binaries);
		return binaries;
	}

	private Cache<String, BinaryResource> lazyCreateCache(Locale locale) {
		String name = format("%s-%s", BINARIES_MAP_CACHE_NAME, locale);
		Cache<String, BinaryResource> cache = manager.getCache(name);
		if (cache == null) {
			cache = builderFactory.create(manager, name).build();
			log.buildNewCache(name);
		}
		return cache;
	}
}
