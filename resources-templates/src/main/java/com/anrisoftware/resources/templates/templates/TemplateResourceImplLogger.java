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

import com.anrisoftware.globalpom.log.AbstractSerializedLogger;

/**
 * Logging messages for {@link TemplateResourceImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class TemplateResourceImplLogger extends AbstractSerializedLogger {

	/**
	 * Creates a logger for {@link TemplateResourceImpl}.
	 * 
	 * @deprecated public scope needed for serialization.
	 */
	@Deprecated
	public TemplateResourceImplLogger() {
		super(TemplateResourceImpl.class);
	}

	void processTemplate(TemplateResourceImpl resource) {
		log.trace("Process the template for {}.", resource);
	}

	void resourceInvalidated(TemplateResourceImpl resource) {
		log.trace("Invalidate resource {}.", resource);
	}

}
