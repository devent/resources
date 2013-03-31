/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-templates.
 *
 * resources-templates is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-templates is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-templates. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.templates;

import static java.lang.String.format;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.templates.api.TemplateResource;

/**
 * Logging messages for {@link TemplatesImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplatesImplLogger extends AbstractLogger {

	private static final String LOADED_RESOURCE_BUNDLE = "Loaded resource bundle {} for '{}'.";
	private static final String NO_TEMPLATE_AVAILABLE = "No template resource available '%s' (%s)";
	private static final String NOT_FIND_RESOURCE_URL = "Could not find resource URL '{}'.";
	private static final String LOCALE = "locale";
	private static final String TEMPLATE_RESOURCE_NOT_FOUND = "No template resource found '%s' (%s)";

	/**
	 * Creates a logger for {@link TemplatesImpl}.
	 */
	TemplatesImplLogger() {
		super(TemplatesImpl.class);
	}

	void checkTemplateLoaded(boolean haveTemplate, String name, Locale locale,
			ResourceBundle bundle) throws ResourcesException {
		if (haveTemplate) {
			return;
		}
		String message = format(TEMPLATE_RESOURCE_NOT_FOUND, name, locale);
		throw logException(new ResourcesException(message, bundle.getClass()
				.toString(), name).addContext(LOCALE, locale), message);
	}

	public URL checkResourceURL(URL url, String urlString) {
		if (url == null) {
			log.warn(NOT_FIND_RESOURCE_URL, urlString);
		}
		return url;
	}

	void checkHaveResource(TemplateResource res, String name, Locale locale,
			ResourceBundle bundle) throws ResourcesException {
		if (res != null) {
			return;
		}
		String message = format(NO_TEMPLATE_AVAILABLE, name, locale);
		throw logException(new ResourcesException(message, bundle.getClass()
				.toString(), name).addContext(LOCALE, locale), message);
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

}
