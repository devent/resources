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

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link TextResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TextResourceImplLogger extends AbstractLogger {

	private static final String ERROR_LOAD_TEXT = "Error loading text resource '%s' (%s)";

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
		String message = format(ERROR_LOAD_TEXT, name, res.getLocale());
		ResourcesException ex = new ResourcesException(e, message, null, name);
		ex.addContext("locale", res.getLocale());
		logException(ex, message);
		return ex;
	}

}
