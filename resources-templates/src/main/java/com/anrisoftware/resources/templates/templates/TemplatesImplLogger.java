/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org> This file is
 * part of resources-templates. resources-templates is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version. resources-templates is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You
 * should have received a copy of the GNU Lesser General Public License along
 * with resources-templates. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.templates;

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

	private static final String NAME = "name";
	private static final String LOADED_RESOURCE_BUNDLE = "Loaded resource bundle {} for '{}'.";
	private static final String TEMPLATE_NOT_AVAILABLE_MESSAGE = "Template resource not available '{}' ({}).";
	private static final String TEMPLATE_NOT_AVAILABLE = "Template resource not available";
	private static final String NOT_FOUND_RESOURCE = "Template resource not found at '{}'.";
	private static final String LOCALE = "locale";
	private static final String TEMPLATE_NOT_FOUND_MESSAGE = "Template resource not found '{}' ({}).";
	private static final String TEMPLATE_NOT_FOUND = "Template resource not found";

	/**
	 * Creates a logger for {@link TemplatesImpl}.
	 */
	TemplatesImplLogger() {
		super(TemplatesImpl.class);
	}

	void checkTemplateLoaded(boolean haveTemplate, String name, Locale locale,
			ResourceBundle bundle) throws ResourcesException {
		if (!haveTemplate) {
			throw logException(new ResourcesException(TEMPLATE_NOT_FOUND,
					bundle.getClass().toString(), name).addContext(LOCALE,
					locale), TEMPLATE_NOT_FOUND_MESSAGE, name, locale);
		}
	}

	public URL checkResourceURL(URL url, String urlString) {
		if (url == null) {
			log.warn(NOT_FOUND_RESOURCE, urlString);
		}
		return url;
	}

	void checkHaveResource(TemplateResource res, String name, Locale locale,
			ResourceBundle bundle) throws ResourcesException {
		if (res == null) {
			throw logException(new ResourcesException(TEMPLATE_NOT_AVAILABLE,
					bundle.getClass().toString(), name).addContext(NAME, name)
					.addContext(LOCALE, locale),
					TEMPLATE_NOT_AVAILABLE_MESSAGE, name, locale);
		}
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
