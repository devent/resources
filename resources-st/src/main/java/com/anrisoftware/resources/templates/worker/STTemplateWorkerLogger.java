/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-st.
 *
 * resources-st is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-st is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-st. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates.worker;

import static org.apache.commons.lang3.Validate.notNull;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.compiler.STException;
import org.stringtemplate.v4.misc.STMessage;

import com.anrisoftware.globalpom.log.AbstractSerializedLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link STTemplateWorker}.
 * 
 * @author Erwin Müller, erwin.mueller@deventm.org
 * @since 1.0
 */
class STTemplateWorkerLogger extends AbstractSerializedLogger {

	/**
	 * Creates a logger for {@link STTemplateWorker}.
	 */
	public STTemplateWorkerLogger() {
		super(STTemplateWorker.class);
	}

	ResourcesException errorOpenGroupFile(STException e) {
		ResourcesException ex = new ResourcesException(e, "", "",
				"Error open the string template group file");
		log.error(ex.getMessage());
		return ex;
	}

	ResourcesException errorProcessTemplate(STMessage errorMessage) {
		ResourcesException ex = new ResourcesException("", "",
				"Error process the template: %s", errorMessage);
		log.error(ex.getMessage());
		return ex;
	}

	void templateProcessed(String name) {
		log.debug("Processed the template ``{}''.", name);
	}

	void checkTemplateCreated(ST template, String name) {
		notNull(template, "Could not load the template ``%s''.", name);
	}

}
