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

import java.net.URL;
import java.util.Properties;

import com.anrisoftware.resources.templates.api.TemplateWorker;
import com.anrisoftware.resources.templates.api.TemplateWorkerFactory;

/**
 * Factory to create a new template worker that is using a <a
 * href=http://www.antlr.org/wiki/display/ST4/StringTemplate+4+Wiki+Home>String
 * Template</a> template engine.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface STTemplateWorkerFactory extends TemplateWorkerFactory {

	static final String DELIMITER_STOP_CHAR_PROPERTY = "template_delimiter_stop_character";

	static final String DELIMITER_START_CHAR_PROPERTY = "template_delimiter_start_character";

	static final String ENCODING_PROPERTY = "template_encoding";

	/**
	 * Creates a new template worker that is using a <a
	 * href=http://www.antlr.org
	 * /wiki/display/ST4/StringTemplate+4+Wiki+Home>String Template</a> template
	 * engine.
	 * 
	 * @param templateUrl
	 *            the {@link URL} of the template group file.
	 * 
	 * @param properties
	 *            the {@link Properties} for the template group file. Have the
	 *            properties:
	 *            <ul>
	 *            <li>{@value #ENCODING_PROPERTY}</li>
	 *            <li>{@value #DELIMITER_START_CHAR_PROPERTY}</li>
	 *            <li>{@value #DELIMITER_STOP_CHAR_PROPERTY}</li>
	 *            </ul>
	 * 
	 * @return the {@link TemplateWorker}.
	 */
	@Override
	TemplateWorker create(URL templateUrl, Properties properties);
}
