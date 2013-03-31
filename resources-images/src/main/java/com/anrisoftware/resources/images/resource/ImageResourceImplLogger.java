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

	private static final String WAIT_WIDTH_AVAILABLE = "Wait until image width available for {}.";
	private static final String WAIT_HEIGHT_AVAILABLE = "Wait until image height available for {}.";
	private static final String RESOURCE_NEEDS_LOADED = "The resource {} needs to be loaded.";
	private static final String RESOURCE_LOADED = "The resource {} is loaded.";
	private static final String ERROR_LOADING_IMAGE_RESOURCE = "Error loading image resource";
	private static final String ERROR_LOADING_IMAGE_RESOURCE_MESSAGE = "Error loading image resource '%s'";

	ImageResourceImplLogger() {
		super(ImageResourceImpl.class);
	}

	ResourcesException errorLoadingImage(ImageResourceImpl res, IOException e) {
		String name = res.getName();
		return logException(new ResourcesException(e,
				ERROR_LOADING_IMAGE_RESOURCE, null, name),
				ERROR_LOADING_IMAGE_RESOURCE_MESSAGE, name);
	}

	void imageIsLoaded(ImageResourceImpl resource, boolean ready) {
		if (ready) {
			log.debug(RESOURCE_LOADED, resource);
		} else {
			log.debug(RESOURCE_NEEDS_LOADED, resource);
		}
	}

	void waitForHeight(ImageResourceImpl resource) {
		log.debug(WAIT_HEIGHT_AVAILABLE, resource);
	}

	void waitForWidth(ImageResourceImpl resource) {
		log.debug(WAIT_WIDTH_AVAILABLE, resource);
	}
}
