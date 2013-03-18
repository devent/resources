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

import static java.lang.String.format;

import java.net.URL;

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

	ResourcesException errorProcessTemplate(STMessage message, URL url) {
		String msg = format("Error process the template: %s", message);
		ResourcesException ex = new ResourcesException(message.cause, msg,
				null, null);
		ex.addContext("message", message);
		ex.addContext("url", url);
		logException(msg, ex);
		return ex;
	}

	void templateProcessed(String name) {
		log.debug("Processed template '{}'.", name);
	}

	void checkTemplateCreated(ST template, String name, URL url) {
		if (template == null) {
			String msg = format("Template not found: '%s' in %s", name, url);
			ResourcesException ex = new ResourcesException(msg, null, null);
			ex.addContext("template name", name);
			ex.addContext("url", url);
			logException(msg, ex);
			throw ex;
		}
	}

}
