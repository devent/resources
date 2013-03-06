/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.images.images;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.images.api.ImageResource;

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
