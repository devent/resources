/*
 * Copyright 2017 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.images.internal.images;

/*-
 * #%L
 * Resources :: Image
 * %%
 * Copyright (C) 2012 - 2018 Advanced Natural Research Institute
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.external.ResourcesException;
import com.anrisoftware.resources.images.external.ImageResource;

/**
 * Logging messages for {@link ImagesImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ImagesWorkerLogger extends AbstractLogger {

	private static final String NAME = "name";
	private static final String ADD_RESIZED_IMAGE = "Resized image resouce {} added.";
	private static final String ERROR_RESIZE = "Error resize image";
	private static final String ERROR_RESIZE_MESSAGE = "Error resize image '{}' ({})";
	private static final String URL_NOT_FOUND = "Image resource '{}' could not be found.";
	private static final String NO_IMAGE = "Image resource not found";
	private static final String NO_IMAGE_MESSAGE = "Image resource not found '{}' ({})";
	private static final String ADD_NEW_IMAGE_RESOUCE = "Image resouce {} added.";
	private static final String LOCALE = "locale";
	private static final String LOADED_RESOURCE_BUNDLE = "Resource bundle {} loaded for '{}'.";

	ImagesWorkerLogger() {
		super(ImagesImpl.class);
	}

	void loadedResourceBundle(String name, ResourceBundle bundle) {
		if (log.isDebugEnabled()) {
			log.debug(LOADED_RESOURCE_BUNDLE, bundleToString(bundle), name);
		}
	}

	private String bundleToString(ResourceBundle bundle) {
		return new ToStringBuilder(bundle).append(LOCALE, bundle.getLocale())
				.toString();
	}

	void addedImageResource(ImageResource image) {
		log.debug(ADD_NEW_IMAGE_RESOUCE, image);
	}

	void checkImageLoaded(boolean haveImage, String name, Locale locale,
			ResourceBundle bundle) throws ResourcesException {
		if (!haveImage) {
			throw logException(new ResourcesException(NO_IMAGE, bundle
					.getClass().toString(), name).addContext(NAME, name)
					.addContext(LOCALE, locale), NO_IMAGE_MESSAGE, name, locale);
		}
	}

	URL checkResourceURL(URL url, String value) {
		if (url == null) {
			log.warn(URL_NOT_FOUND, value);
		}
		return url;
	}

	ResourcesException errorResizeImage(Exception e, String name,
			Locale locale, ResourceBundle bundle) {
		return logException(new ResourcesException(e, ERROR_RESIZE, bundle
				.getClass().toString(), name).addContext(NAME, name)
				.addContext(LOCALE, locale), ERROR_RESIZE_MESSAGE, name, locale);
	}

	void resizedImage(ImageResource image) {
		log.debug(ADD_RESIZED_IMAGE, image);
	}

}
