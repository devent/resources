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
package com.anrisoftware.resources.templates.api;

import java.net.URL;
import java.util.Properties;

/**
 * Factory to create a new template worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TemplateWorkerFactory {

	/**
	 * Creates a new template worker.
	 * 
	 * @param templateUrl
	 *            the {@link URL} of the template file.
	 * 
	 * @param properties
	 *            the {@link Properties} for the template.
	 * 
	 * @return the {@link TemplateWorker}.
	 */
	TemplateWorker create(URL templateUrl, Properties properties);
}
