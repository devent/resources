/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
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

	/**
	 * Creates a logger for {@link TemplatesImpl}.
	 */
	TemplatesImplLogger() {
		super(TemplatesImpl.class);
	}

	void checkTemplateLoaded(boolean haveTemplate, ResourceBundle bundle,
			String name) throws ResourcesException {
		if (!haveTemplate) {
			ResourcesException ex = new ResourcesException(bundle.getClass()
					.getName(), name, "No template resource loaded for ``%s''",
					name);
			log.error(ex.getMessage());
			throw ex;
		}
	}

	public URL checkResourceURL(URL url, String urlString) {
		if (url == null) {
			log.warn("The resource URL ``{}'' could not be found.", urlString);
		}
		return url;
	}

	void checkHaveResource(TemplateResource res, ResourceBundle bundle,
			String name, Locale locale) throws ResourcesException {
		if (res == null) {
			ResourcesException ex = new ResourcesException(bundle.getClass()
					.getName(), name,
					"No template resource ``%s'' available for the locale %s",
					name, locale);
			log.error(ex.getMessage());
			throw ex;
		}
	}

	void loadedResourceBundle(String name, ResourceBundle bundle) {
		if (log.isDebugEnabled()) {
			log.debug(
					"Loaded the resource bundle {} for the text resource ``{}''.",
					bundleToString(bundle), name);
		}
	}

	private String bundleToString(ResourceBundle bundle) {
		return new ToStringBuilder(bundle).append("locale", bundle.getLocale())
				.toString();
	}

}
