/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import java.io.IOException;
import java.util.Locale;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.external.ResourcesException;

/**
 * Logging messages for {@link TextResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceImplLogger extends AbstractLogger {

	private static final String LOCALE = "locale";
	private static final String NAME = "name";
	private static final String ERROR_LOAD_MESSAGE = "Error load text resource '{}' ({})";
	private static final String ERROR_LOAD = "Error load text resource";

	/**
	 * Creates a logger for {@link TextResourceImpl}.
	 * 
	 * @since 1.2
	 */
	public TextResourceImplLogger() {
		super(TextResourceImpl.class);
	}

	ResourcesException errorLoadText(TextResourceImpl res, IOException e) {
		String name = res.getName();
		Locale locale = res.getLocale();
		return logException(new ResourcesException(e, ERROR_LOAD, null, name)
				.addContext(NAME, name).addContext(LOCALE, locale),
				ERROR_LOAD_MESSAGE, name, locale);
	}
}
