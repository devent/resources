/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-binary.
 *
 * resources-binary is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-binary is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-binary. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.binary.binaries;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.binary.api.BinaryResource;

/**
 * Logging messages for {@link BinariesImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class BinariesWorkerLogger extends AbstractLogger {

	private static final String RESOURCE_URL_NOT_FOUND = "Resource URL not found '{}'.";
	private static final String NO_RESOURCE = "Binary resource not found";
	private static final String NO_RESOURCE_MESSAGE = "Binary resource not found '{}' ({}).";
	private static final String ADD_NEW_BINARY_RESOUCE = "Binary resouce {} added.";
	private static final String LOADED_RESOURCE_BUNDLE = "Resource bundle {} loaded for '{}'.";

	BinariesWorkerLogger() {
		super(BinariesImpl.class);
	}

	void loadedResourceBundle(String name, ResourceBundle bundle) {
		if (log.isDebugEnabled()) {
			log.debug(LOADED_RESOURCE_BUNDLE, bundleToString(bundle), name);
		}
	}

	private String bundleToString(ResourceBundle bundle) {
		return new ToStringBuilder(bundle).append("locale", bundle.getLocale())
				.toString();
	}

	void addedBinaryResource(BinaryResource resource) {
		log.debug(ADD_NEW_BINARY_RESOUCE, resource);
	}

	void checkBinaryLoaded(boolean haveResource, String name, Locale locale,
			ResourceBundle bundle) throws ResourcesException {
		if (!haveResource) {
			throw logException(new ResourcesException(NO_RESOURCE, bundle
					.getClass().toString(), name).addContext("name", name)
					.addContext("locale", locale), NO_RESOURCE_MESSAGE, name,
					locale);
		}
	}

	URL checkResourceURL(URL url, String value) {
		if (url == null) {
			log.warn(RESOURCE_URL_NOT_FOUND, value);
		}
		return url;
	}

}
