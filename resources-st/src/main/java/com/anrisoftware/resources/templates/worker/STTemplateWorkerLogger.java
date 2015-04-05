/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import java.net.URL;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.compiler.STException;
import org.stringtemplate.v4.misc.STMessage;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging messages for {@link STTemplateWorker}.
 * 
 * @author Erwin Müller, erwin.mueller@deventm.org
 * @since 1.0
 */
class STTemplateWorkerLogger extends AbstractLogger {

	private static final String URL = "url";
	private static final String TEMPLATE_NAME = "template name";
	private static final String TEMPLATE_NOT_FOUND = "Template not found";
	private static final String TEMPLATE_NOT_FOUND_MESSAGE = "Template not found: '{}' in {}";
	private static final String PROCESSED_TEMPLATE = "Processed template '{}'.";
	private static final String MESSAGE = "message";
	private static final String ERROR_PROCESS_TEMPLATE = "Error process template";
	private static final String ERROR_PROCESS_TEMPLATE_MESSAGE = "Error process the template {}: {}";
	private static final String ERROR_OPEN_GROUP_FILE = "Error open ST group file";
	private static final String ERROR_OPEN_GROUP_FILE_MESSAGE = "Error open ST group file: {}.";

	/**
	 * Creates a logger for {@link STTemplateWorker}.
	 */
	public STTemplateWorkerLogger() {
		super(STTemplateWorker.class);
	}

	ResourcesException errorOpenGroupFile(STException e) {
		return logException(new ResourcesException(e, "", "",
				ERROR_OPEN_GROUP_FILE), ERROR_OPEN_GROUP_FILE_MESSAGE,
				e.getMessage());
	}

	ResourcesException errorProcessTemplate(STMessage message, URL url) {
		throw logException(new ResourcesException(message.cause,
				ERROR_PROCESS_TEMPLATE, null, null)
				.addContext(MESSAGE, message).addContext(URL, url),
				ERROR_PROCESS_TEMPLATE_MESSAGE, url, message);
	}

	void templateProcessed(String name) {
		log.debug(PROCESSED_TEMPLATE, name);
	}

	void checkTemplateCreated(ST template, String name, URL url) {
		if (template == null) {
			throw logException(new ResourcesException(TEMPLATE_NOT_FOUND, null,
					null).addContext(TEMPLATE_NAME, name).addContext(URL, url),
					TEMPLATE_NOT_FOUND_MESSAGE, name, url);
		}
	}

}
