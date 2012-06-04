package com.anrisoftware.resources.images.maps;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ImageResource;
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
