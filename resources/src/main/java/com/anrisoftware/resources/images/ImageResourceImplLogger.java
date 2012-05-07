package com.anrisoftware.resources.images;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for the {@link ImageResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImageResourceImplLogger extends AbstractLogger {

	ImageResourceImplLogger() {
		super(ImageResourceImpl.class);
	}

	ResourcesException errorLoadingImage(ImageResourceImpl resource,
			IOException e) {
		ResourcesException ex = new ResourcesException(e,
				"Error loading the image resource %s", resource);
		log.error(e.getMessage());
		return ex;
	}

	void imageIsLoaded(ImageResourceImpl resource, boolean ready) {
		if (ready) {
			log.debug("The resource {} is loaded.", resource);
		} else {
			log.debug("The resource {} needs to be loaded.", resource);
		}
	}

	void waitForHeight(ImageResourceImpl resource) {
		log.debug(
				"Wait until the image height is available for image resource {}.",
				resource);
	}

	void waitForWidth(ImageResourceImpl resource) {
		log.debug(
				"Wait until the image width is available for image resource {}.",
				resource);
	}
}
