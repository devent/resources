/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-images.
 *
 * resources-images is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-images is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-images. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.images.mapcachedresolutions;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.anrisoftware.resources.images.api.BundlesMap;
import com.anrisoftware.resources.images.api.ImagesMap;
import com.anrisoftware.resources.images.api.ImagesMapFactory;

/**
 * Uses a {@link HashMap} to store the images for each resource bundle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.1
 */
class BundlesMapImpl implements BundlesMap {

	private final Map<ResourceBundle, ImagesMap> map;

	private final ImagesMapFactory factory;

	@Inject
	BundlesMapImpl(ImagesMapFactory imagesFactory) {
		this.map = new HashMap<ResourceBundle, ImagesMap>();
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
