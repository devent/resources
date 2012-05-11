package com.anrisoftware.resources.images;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ImageResource;

/**
 * Logger messages for the {@link ImagesMap}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesMapLogger extends AbstractLogger {

	ImagesMapLogger() {
		super(ImagesMap.class);
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
