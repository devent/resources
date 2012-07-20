/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.resources.st.worker;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Named;

import com.anrisoftware.propertiesutils.ContextPropertiesFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * Binds the String Template template resource default properties.
 * <p>
 * Loads the {@code stringtemplate.properties} from the template worker class
 * path.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class STTemplateDefaultPropertiesModule extends AbstractModule {

	@Override
	protected void configure() {
	}

	@Provides
	@Named("st-default-properties")
	Properties getSTDefaultProperties() throws IOException {
		return new ContextPropertiesFactory(STTemplateWorker.class)
				.fromResource(STTemplateDefaultPropertiesModule.class,
						"stringtemplate.properties");
	}
}
