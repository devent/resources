/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.images.maps;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.images.api.ImageResource;
import com.anrisoftware.resources.images.api.ImagesMap;

/**
 * Logger messages for the {@link ImagesMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesMapLogger extends AbstractLogger {

	ImagesMapLogger() {
		super(ImagesMapImpl.class);
	}

	void imageAlreadyInMap(ImagesMap imagesMap, ImageResource image) {
		log.warn("The image resource {} is already in the map {}.", image,
				imagesMap);
	}

	void noImageReturningNearest(ImagesMap imagesMap, String name) {
		log.warn(
				"No image ``{}'' with the size found in the map {}, returning the next nearest.",
				name, imagesMap);
	}

}
