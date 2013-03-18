/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
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

import static java.lang.System.getProperties;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.inject.Named;

import com.anrisoftware.propertiesutils.ContextPropertiesFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * Binds the String Template template resource default properties.
 * <p>
 * Loads the {@code stringtemplate.properties} from the template worker class
 * path. The properties can be overridden by system properties.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @see STTemplateWorkerFactory#DELIMITER_START_CHAR_PROPERTY
 * @see STTemplateWorkerFactory#DELIMITER_STOP_CHAR_PROPERTY
 * @see STTemplateWorkerFactory#ENCODING_PROPERTY
 * @see System#getProperties()
 * @since 1.2
 */
public class STDefaultPropertiesModule extends AbstractModule {

	static final URL PROPERTIES_URL = STDefaultPropertiesModule.class
			.getResource("stringtemplate.properties");

	@Override
	protected void configure() {
	}

	@Provides
	@Named("st-default-properties")
	Properties getSTDefaultProperties() throws IOException {
		return new ContextPropertiesFactory(STTemplateWorker.class)
				.withProperties(getProperties()).fromResource(PROPERTIES_URL);
	}
}
