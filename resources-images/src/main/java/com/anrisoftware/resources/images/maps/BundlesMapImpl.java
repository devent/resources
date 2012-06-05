package com.anrisoftware.resources.images.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.images.api.BundlesMap;
import com.anrisoftware.resources.images.api.ImagesMap;
import com.anrisoftware.resources.images.api.ImagesMapFactory;
import com.google.common.collect.Maps;

/**
 * <p>
 * Uses a {@link HashMap} to store the images for each resource bundle.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class BundlesMapImpl implements BundlesMap {

	private final Map<ResourceBundle, ImagesMap> map;

	private final ImagesMapFactory factory;

	@Inject
	BundlesMapImpl(ImagesMapFactory imagesFactory) {
		this.map = Maps.newHashMap();
		this.factory = imagesFactory;
	}

	@Override
	public ImagesMap getImages(ResourceBundle bundle) {
		ImagesMap images = map.get(bundle);
		if (images == null) {
			images = factory.create();
			map.put(bundle, images);
		}
		return images;
	}
}
