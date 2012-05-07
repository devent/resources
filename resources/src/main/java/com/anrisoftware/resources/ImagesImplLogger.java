package com.anrisoftware.resources;

import java.net.URL;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ImageResolution;
import com.anrisoftware.resources.api.ImageResource;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link ImagesImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesImplLogger extends AbstractLogger {

	ImagesImplLogger() {
		super(ImagesImpl.class);
	}

	void addedImageResource(String name, ImageResolution resolution,
			ImageResource image) {
		log.debug("Add new image resouce {} named {} with resolution {}.",
				new Object[] { image, name, resolution });
	}

	void checkImageLoaded(boolean haveImage, String name)
			throws ResourcesException {
		if (!haveImage) {
			ResourcesException ex = new ResourcesException(
					"No image resource loaded for ``%s''", name);
			log.error(ex.getMessage());
			throw ex;
		}
	}

	URL checkResourceURL(URL url, String value) {
		if (url == null) {
			log.warn("The resource URL ``{}'' could not be found.", value);
		}
		return url;
	}

	ResourcesException errorResizeImage(Exception e) {
		ResourcesException ex = new ResourcesException(e, "Error resize image");
		log.error(ex.getMessage());
		return ex;
	}

	void addResizedImage(ImageResource res, String name,
			ImageResolution resolution) {
		log.debug("Add resized image resouce {} named {} with resolution {}.",
				new Object[] { res, name, resolution });
	}

}
