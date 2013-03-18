/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of resources-templates-tests.
 *
 * resources-templates-tests is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * resources-templates-tests is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with resources-templates-tests. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.resources.templates

import org.junit.Before

import com.anrisoftware.globalpom.utils.TestUtils
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates the environment to unit test the template resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractTemplateResourceTestUtils extends TestUtils {

	Injector injector

	def factory

	def resourceFactory

	@Before
	void createFactories() {
		injector = createInjector()
		factory = createFactory()
		resourceFactory = createResourceFactory()
	}

	/**
	 * Creates the needed modules if they are not already have been created.
	 */
	Injector createInjector() {
		if (injector != null) {
			injector
		}
		Guice.createInjector(templatesModule, templateWorkerModule,
						templatesMapModule, propertiesModule)
	}

	/**
	 * Create the templates factory.
	 */
	abstract createFactory()

	/**
	 * Create the template resource factory.
	 */
	abstract createResourceFactory()

	/**
	 * Returns the module that binds the template resources.
	 */
	abstract getTemplatesModule()

	/**
	 * Returns the module that binds the template worker.
	 */
	abstract getTemplateWorkerModule()

	/**
	 * Returns the module that binds the template resources maps.
	 */
	abstract getTemplatesMapModule()

	/**
	 * Returns the module that binds the template properties.
	 */
	abstract getPropertiesModule()

	/**
	 * Returns the URL of the template.
	 */
	abstract getTemplateUrl()

	/**
	 * Returns the template properties.
	 */
	abstract getTemplateProperties()
}
