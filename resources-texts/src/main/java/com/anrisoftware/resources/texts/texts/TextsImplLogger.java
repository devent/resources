/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-texts.
 *
 * resources-texts is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-texts is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-texts. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.texts.texts;

import static java.lang.String.format;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.texts.api.TextResource;

/**
 * Logging messages for {@link TextsImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextsImplLogger extends AbstractLogger {

	private static final String RESOURCE_URL_NOT_FOUND = "The resource URL ``{}'' could not be found.";

	private static final String LOADED_RESOURCE_BUNDLE = "Loaded the resource bundle {} for the text resource ``{}''.";

	private static final String NO_TEXT_RESOURCE_AVAILABLE = "No text resource available '%s' (%s)";

	private static final String NO_TEXT_RESOURCE_LOADED = "No text resource loaded '%s' (%s)";

	/**
	 * Creates a logger for {@link TextsImpl}.
	 */
	TextsImplLogger() {
		super(TextsImpl.class);
	}

	void checkTextLoaded(boolean haveText, String name, Locale locale,
			ResourceBundle bundle) throws ResourcesException {
		if (!haveText) {
			String message = format(NO_TEXT_RESOURCE_LOADED, name, locale);
			ResourcesException ex = new ResourcesException(message, bundle
					.getClass().getName(), name);
			ex.addContext("locale", locale);
			logException(ex, message);
			throw ex;
		}
	}

	public URL checkResourceURL(URL url, String urlString) {
		if (url == null) {
			log.warn(RESOURCE_URL_NOT_FOUND, urlString);
		}
		return url;
	}

	void checkHaveResource(TextResource text, String name, Locale locale,
			ResourceBundle bundle) throws ResourcesException {
		if (text == null) {
			String message = format(NO_TEXT_RESOURCE_AVAILABLE, name, locale);
			ResourcesException ex = new ResourcesException(message, bundle
					.getClass().getName(), name);
			ex.addContext("locale", locale);
			logException(ex, message);
			throw ex;
		}
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

}
