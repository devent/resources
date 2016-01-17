/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.templates.maps;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link TemplatesMapImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplatesMapLogger extends AbstractLogger {

	private static final String CONTAINS_RESOURCE = "Map contains template resource '{}'.";

	/**
	 * Creates a logger for {@link TemplatesMapImpl}.
	 */
	TemplatesMapLogger() {
		super(TemplatesMapImpl.class);
	}

	void textAlreadyInMap(String name) {
		log.warn(CONTAINS_RESOURCE, name);
	}

}
