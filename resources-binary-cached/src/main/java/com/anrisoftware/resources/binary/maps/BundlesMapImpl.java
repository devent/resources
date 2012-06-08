package com.anrisoftware.resources.binary.maps;

import static com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.BINARIES_CACHE_FACTORY;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.cache.Cache;
import javax.inject.Inject;
import javax.inject.Named;

import com.anrisoftware.resources.api.BinaryResource;
import com.anrisoftware.resources.binary.api.BinariesCacheKey;
import com.anrisoftware.resources.binary.api.BinariesMap;
import com.anrisoftware.resources.binary.api.BinariesMapFactory;
import com.anrisoftware.resources.binary.api.BundlesMap;
import com.anrisoftware.resources.binary.maps.BinaryResourcesCacheBinderModule.CacheFactory;
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

	private final Map<ResourceBundle, BinariesMap> map;

	private final BinariesMapFactory binariesFactory;

	private final CacheFactory cacheFactory;

	@Inject
	BundlesMapImpl(BinariesMapFactory binariesFactory,
			@Named(BINARIES_CACHE_FACTORY) CacheFactory cacheFactory) {
		this.map = Maps.newHashMap();
		this.binariesFactory = binariesFactory;
		this.cacheFactory = cacheFactory;
	}

	@Override
	public BinariesMap getBinaries(String baseName, ResourceBundle bundle) {
		BinariesMap binaries = map.get(bundle);
		if (binaries == null) {
			binaries = createMap(baseName, bundle);
		}
		return binaries;
	}

	private BinariesMap createMap(String baseName, ResourceBundle bundle) {
		BinariesMapImpl binaries = (BinariesMapImpl) binariesFactory.create();
		binaries.setCache(createCache());
		binaries.setBaseName(baseName);
		binaries.setLocale(bundle.getLocale());
		map.put(bundle, binaries);
		return binaries;
	}

	private Cache<BinariesCacheKey, BinaryResource> createCache() {
		return cacheFactory.create();
	}
}
