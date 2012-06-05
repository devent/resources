package com.anrisoftware.resources.images;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ImageResource;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link ImagesImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesWorkerLogger extends AbstractLogger {

	ImagesWorkerLogger() {
		super(ImagesImpl.class);
	}

	void loadedResourceBundle(String name, ResourceBundle bundle) {
		if (log.isDebugEnabled()) {
			log.debug(
					"Loaded the resource bundle {} for the image resource ``{}''.",
					bundleToString(bundle), name);
		}
	}

	private String bundleToString(ResourceBundle bundle) {
		return new ToStringBuilder(bundle).append("locale", bundle.getLocale())
				.toString();
	}

	void addedImageResource(ImageResource image) {
		log.debug("Add new image resouce {}.", image);
	}

	void checkImageLoaded(boolean haveImage, String name)
			throws ResourcesException {
		if (!haveImage) {
			ResourcesException ex = new ResourcesException("", name,
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

	ResourcesException errorResizeImage(Exception e, String name) {
		ResourcesException ex = new ResourcesException(e, name,
				"Error resize image");
		log.error(ex.getMessage());
		return ex;
	}

	void resizedImage(ImageResource image) {
		log.debug("Add resized image resouce {}.", image);
	}

}
