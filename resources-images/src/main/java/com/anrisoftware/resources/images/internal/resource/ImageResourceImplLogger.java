/**
 * Copyright © 2012 Erwin Müller (erwin.mueller@anrisoftware.com)
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

package com.anrisoftware.resources.images.internal.resource;


import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.external.ResourcesException;

class ImageResourceImplLogger extends AbstractLogger {

	private static final String WAIT_WIDTH_AVAILABLE = "Wait until image width available for {}.";
	private static final String WAIT_HEIGHT_AVAILABLE = "Wait until image height available for {}.";
	private static final String RESOURCE_NEEDS_LOADED = "Image resource {} waiting to load.";
	private static final String RESOURCE_LOADED = "Image resource {} loaded.";
	private static final String ERROR_LOAD = "Error loading image resource";
	private static final String ERROR_LOAD_MESSAGE = "Error loading image resource '%s'.";

	ImageResourceImplLogger() {
		super(ImageResourceImpl.class);
	}

	ResourcesException errorLoadingImage(ImageResourceImpl res, IOException e) {
		String name = res.getName();
		return logException(new ResourcesException(e, ERROR_LOAD, null, name),
				ERROR_LOAD_MESSAGE, name);
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
