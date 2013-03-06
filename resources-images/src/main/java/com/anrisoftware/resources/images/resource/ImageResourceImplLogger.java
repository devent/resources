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
package com.anrisoftware.resources.images.resource;

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
		ResourcesException ex = new ResourcesException(e, resource.getName(),
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
