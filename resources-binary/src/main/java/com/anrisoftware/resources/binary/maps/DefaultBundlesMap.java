package com.anrisoftware.resources.binary.maps;

import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.binary.api.BinariesMap;
import com.anrisoftware.resources.binary.api.BinariesMapFactory;
import com.anrisoftware.resources.binary.api.BundlesMap;
import com.google.common.collect.Maps;

/**
 * <p>
 * Uses Java hash map to store the binaries for each resource bundle.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class DefaultBundlesMap implements BundlesMap {

	private final Map<ResourceBundle, BinariesMap> map;

	private final BinariesMapFactory factory;

	@Inject
	DefaultBundlesMap(BinariesMapFactory imagesFactory) {
		this.map = Maps.newHashMap();
		this.factory = imagesFactory;
	}

	@Override
	public BinariesMap getBinaries(String baseName, ResourceBundle bundle) {
		BinariesMap images = map.get(bundle);
		if (images == null) {
			images = factory.create();
			map.put(bundle, images);
		}
		return images;
	}

	@Override
	public String toString() {
		return map.toString();
	}
}
