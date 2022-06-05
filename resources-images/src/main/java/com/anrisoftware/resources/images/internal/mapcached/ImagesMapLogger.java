/*
 * Copyright 2012-2022 Erwin Müller <erwin.mueller@anrisoftware.com>
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


import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.images.external.ImageResource;
import com.anrisoftware.resources.images.external.ImagesMap;

class ImagesMapLogger extends AbstractLogger {

	private static final String NO_IMAGE = "Image resource '{}' with the size not found in the map, returning the next nearest.";
	private static final String ALREADY_MAP = "Image resource {} already in map {}.";

	ImagesMapLogger() {
		super(ImagesMapImpl.class);
	}

	void imageAlreadyInMap(ImagesMap imagesMap, ImageResource image) {
		log.warn(ALREADY_MAP, image, imagesMap);
	}

	void noImageReturningNearest(ImagesMap imagesMap, String name) {
		log.warn(NO_IMAGE, name);
	}

}
