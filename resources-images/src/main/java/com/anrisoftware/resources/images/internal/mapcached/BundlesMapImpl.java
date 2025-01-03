/*
 * Copyright 2012-2025 Erwin Müller <erwin.mueller@anrisoftware.com>
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
package com.anrisoftware.resources.images.internal.mapcached;


import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import jakarta.inject.Inject;

import com.anrisoftware.resources.images.external.ImagesBundlesMap;
import com.anrisoftware.resources.images.external.ImagesMap;
import com.anrisoftware.resources.images.external.ImagesMapFactory;

class BundlesMapImpl implements ImagesBundlesMap {

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
